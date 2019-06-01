package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class CheckBalance {
    public CheckBalance(String sessionToken) {
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


    public void check_balance() {

        try {
            java.net.URL transactionURL = new java.net.URL("https://trust-uat.paytm.in/wallet-web/checkBalance");


            HttpURLConnection connection = (HttpURLConnection) transactionURL.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("ssotoken", sessionToken);
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
