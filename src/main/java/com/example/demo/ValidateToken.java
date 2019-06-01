package com.example.demo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ValidateToken {
    // for production
    // URL transactionURL = new URL("https://accounts.paytm.com/user/details");

    public ValidateToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    String sessionToken = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";

    String responseData;


    public String getResponseData() {
        return responseData;
    }


    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }





    public void validate_token() {

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
            //return responseData;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
