package com.example.demo;

import com.paytm.pg.merchant.CheckSumServiceHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;


public class Refund {
    public Refund() {
    }

    String responseData;

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    TransactionStatusResponse txnstatres;
    ValidateTokenResponse valt;
    final String MERCHANT_MID = "Delvit07224170213556";
    final String txnType = "REFUND";
    final String orderId = "ORDER_123456789";
    final String txnId = txnstatres.getTXNID();
    final String refId = valt.getMobile();
    final String refundAmount = "1.00";
    final String MERCHANT_KEY = "&!vj74@Ri&g6U1TI";







    public void refund_init() {

        TreeMap<String, String> paytmParams = new TreeMap<String, String>();
        paytmParams.put("MID", MERCHANT_MID);
        paytmParams.put("txnType", txnType);
        paytmParams.put("orderId", orderId);
        paytmParams.put("txnId", txnId);
        paytmParams.put("refId", refId);
        paytmParams.put("refundAmount", refundAmount);




        // URL url = new URL("https://securegw.paytm.in/refund/api/v1/async/refund"); // for production


        try {
            URL url = new URL("https://securegw-stage.paytm.in/refund/api/v1/async/refund"); // for staging
            String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MERCHANT_KEY, paytmParams);
            String paytmParams_head = "{\"clientId\":\"C11\",\"version\":\"v1\",\"signature\":\"" + checksum + "\"}";
            String paytmParams_body = "{\"mid\":\"" + MERCHANT_MID + "\",\"txnType\":\"REFUND\",\"orderId\":\"xxxxxxxxxxxxxxxx\",\"txnId\":\""+txnstatres.getTXNID()+ "\",\"refId\":\""+valt.getMobile()+ "\",\"refundAmount\":\"1.00\"}";
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
