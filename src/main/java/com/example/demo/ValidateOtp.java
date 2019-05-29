package com.example.demo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.TreeMap;

public class ValidateOtp {

    String otp = "xxxxxx";
    String state = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
    String clientId = "merchant-perpule-stg";
    String clientSecret="jlgBKygCagp6PKECMEbLBfCviJorleAj";


    String responseData;

    public String getResponseData() {
        return responseData;
    }

    public ValidateOtp(String otp, String state) {
        this.otp = otp;
        this.state = state;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void validate_OTP(){

        String auth = "Basic " + "bWVyY2hhbnQtcGVycHVsZS1zdGc6amxnQkt5Z0NhZ3A2UEtFQ01FYkxCZkN2aUpvcmxlQWo=";

        TreeMap<String, String> paytmParams = new TreeMap<String, String>();
        paytmParams.put("otp", otp);
        paytmParams.put("state", state);

        try {

            URL transactionURL= new URL("https://accounts-uat.paytm.com/signin/validate/otp");

            JSONObject obj = new JSONObject(paytmParams);
            String postData = obj.toString();
            HttpURLConnection connection = (HttpURLConnection) transactionURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", auth);
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(postData);
            requestWriter.close();

            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response Json = " + responseData);
            }
            System.out.append("Requested Json = " + postData + " ");
            responseReader.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
