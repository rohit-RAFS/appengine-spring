package com.example.demo;
import com.example.demo.Model.DatabaseController;
import com.google.gson.Gson;
import com.paytm.pg.merchant.CheckSumServiceHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

public class CheckBalance {
    String responseData;

    String mid = "Delvit07224170213556";
    String orderId = "ORDER_123456789";
    String userToken = "";
    String totalAmount = "0.00";
    String MERCHANT_KEY = "&!vj74@Ri&g6U1TI";
    String phonenumber="0";

    public CheckBalance() {
    }

    public CheckBalance(String phonenumber,String bill) {
        this.phonenumber = phonenumber;
        this.totalAmount=bill;
    }
    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }


    public void check_balance(){

        //Getting access token from table
        try {
            Connection conn = DatabaseController.getConnection();
            PreparedStatement getstmt = conn.prepareStatement("SELECT * FROM Customer WHERE PhoneNumber LIKE ?");
            getstmt.setString(1, "%" + phonenumber + "%");
            ResultSet rs = getstmt.executeQuery();
            SQLTableEntry sl = new SQLTableEntry();
            sl.SQLRetrieve(rs);
            userToken=sl.getAccessToken();

        }catch (Exception e){e.printStackTrace();}

        //Creating tree map
        TreeMap<String, String> paytmParams = new TreeMap<String, String>();
        paytmParams.put("mid", mid);
        paytmParams.put("userToken", userToken);
        paytmParams.put("totalAmount", totalAmount);

        try {
            URL url = new URL("https://securegw-stage.paytm.in/paymentservices/pay/consult"); // for staging
            String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MERCHANT_KEY, paytmParams);

            String paytmParams_head = "{\"clientId\":\"merchant-perpule-stg\",\"version\":\"V1\",\"requestTimestamp\":\"1569820811000\",\"channelId\":\"WAP\",\"signature\":\""+checksum+"\"}";
            String paytmParams_body = "{\"userToken\":\"" + userToken + "\",\"totalAmount\":\""+totalAmount+"\",\"mid\":\""+mid+"\"}";
            String post_data = "{\"body\":" + paytmParams_body + ",\"head\":" + paytmParams_head + "}";



            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(post_data);
            requestWriter.close();
            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response: " + responseData);
            }
            // System.out.append("Request: " + post_data + " ");
            responseReader.close();


            //Evaluate response:
            //TODO error handling for failure response
            Gson g=new Gson();
            CheckBalanceResponse checkBalanceResponse= g.fromJson(responseData,CheckBalanceResponse.class);

            responseData=checkBalanceResponse.getBody().getDeficitAmount();

        } catch (Exception exception) {
            exception.printStackTrace();
        }




    }


}