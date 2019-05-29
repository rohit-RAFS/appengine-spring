package com.example.demo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class SendOtp {
	
	String email = "xxxxxxxx@xxxxxx.xxx";
	String phone = "xxxxxxxxxx";
	String clientId = "merchant-perpule-stg";
	String scope = "wallet";
	String responseType = "token";
	String merchantKey = "&!vj74@Ri&g6U1TI";
	
	public SendOtp(String email, String phone) {
		this.email = email;
		this.phone = phone;
	}
	
	public void Send_OTP() {
		
		
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		paytmParams.put("email", email);
		paytmParams.put("phone", phone);
		paytmParams.put("clientId", clientId);
		paytmParams.put("scope", scope);
		paytmParams.put("responseType", responseType);
		
		try {
			URL transactionURL = new URL("https://accounts-uat.paytm.com/signin/otp");
			JSONObject obj = new JSONObject(paytmParams);
			String postData = obj.toString();
			
			HttpURLConnection connection = (HttpURLConnection) transactionURL.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
			System.out.append("Requested Json = " + postData + " ");
			responseReader.close();
			//return responseData;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
