package com.example.demo;


import com.example.demo.Model.DatabaseController;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidateToken {
    // for production
    // URL transactionURL = new URL("https://accounts.paytm.com/user/details");


    String phonenumber="0";
    String sessionToken = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
    String responseData;

    public ValidateToken(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }


    public String getResponseData() {
        return responseData;
    }
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }


    public void validate_token() {


        //retrieve state for given phone number
        try {
            Connection conn = DatabaseController.getConnection();
            PreparedStatement getstmt = conn.prepareStatement("SELECT * FROM Customer WHERE PhoneNumber LIKE ?");
            getstmt.setString(1, "%" + phonenumber + "%");
            ResultSet rs = getstmt.executeQuery();
            SQLTableEntry sl = new SQLTableEntry();
            sl.SQLRetrieve(rs);
            sessionToken=sl.getAccessToken();

        }catch (Exception e){e.printStackTrace();}


        try {
            java.net.URL transactionURL= new java.net.URL("https://accounts-uat.Paytm.com/user/details");
            HttpURLConnection connection = (HttpURLConnection) transactionURL.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("session_token", sessionToken);
            connection.setUseCaches(false);
            connection.setDoOutput(true);


            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response Json = " + responseData);
            }
            responseReader.close();

            //Evaluate response:
            //TODO error handling for failure response
            Gson g=new Gson();
            ValidateTokenResponse validateTokenResponse= g.fromJson(responseData,ValidateTokenResponse.class);


            Connection conn = DatabaseController.getConnection();
            PreparedStatement setstmt = conn.prepareStatement(
                    "UPDATE Customer SET Expires = ? WHERE PhoneNumber LIKE ?;");
            setstmt.setString(1, validateTokenResponse.getExpires());
            setstmt.setString(2, "%"+phonenumber+"%");
            setstmt.execute();
            responseData="exists";

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
