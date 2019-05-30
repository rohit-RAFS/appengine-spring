package com.example.demo;

import com.example.demo.Model.DatabaseController;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.TreeMap;

public class SendOtp {
	
	String email = "xxxxxxxx@xxxxxx.xxx";
	String phone = "xxxxxxxxxx";
	String clientId = "merchant-perpule-stg";
	String scope = "wallet";
	String responseType = "token";
	String merchantKey = "&!vj74@Ri&g6U1TI";
	String responseData;

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

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

			InputStream is = connection.getInputStream();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
			if ((responseData = responseReader.readLine()) != null) {
				System.out.append("Response Json = " + responseData);
			}
			System.out.append("Requested Json = " + postData + " ");
			responseReader.close();
			
			
			Connection conn = DatabaseController.getConnection();
			PreparedStatement setState = conn.prepareStatement(
					"INSERT INTO OtpState (U_ID, U_State) VALUES (?, ?);");
			setState.setString(1, phone + responseData );
			setState.setString(2, responseData);
			// TODO Set NUMBER & state
			// Finally, execute the statement. If it fails, an error will be thrown.
			setState.execute();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
