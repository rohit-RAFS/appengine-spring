package com.example.demo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RevokeAccess {

// for production
// URL transactionURL = new URL("https://accounts.paytm.com/oauth2/accessToken/");

    String session_token = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";


    public RevokeAccess(String session_token) {
        this.session_token = session_token;
    }
    String responseData;

    public String getResponseData() {
        return responseData;
    }



    String auth = "Basic " + "bWVyY2hhbnQtcGVycHVsZS1zdGc6amxnQkt5Z0NhZ3A2UEtFQ01FYkxCZkN2aUpvcmxlQWo=";

    public void revoke_access() {

        try {
            URL transactionURL = new URL("https://accounts-uat.paytm.com/oauth2/accessToken/"+session_token);
            String postData = "";
            HttpURLConnection connection = (HttpURLConnection) transactionURL.openConnection();
            connection.setRequestMethod("DELETE");
            connection.addRequestProperty("Authorization", auth);
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(postData);
            requestWriter.close();
            String responseData = "";
            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response Json = " + responseData);
            }
            responseReader.close();
           // return responseData;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}



