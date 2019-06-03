package com.example.demo;

import com.example.demo.Model.DatabaseController;
import com.google.gson.Gson;
import org.json.JSONObject;

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
import java.util.logging.Logger;

public class SendOtp {
	
	String email = "xxxxxxxx@xxxxxx.xxx";
	String phone = "xxxxxxxxxx";
	String clientId = "merchant-perpule-stg";
	String scope = "wallet";
	String responseType = "token";
	String merchantKey = "&!vj74@Ri&g6U1TI";
	String responseData;
	private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());


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


			//Saving state from response received for particular phone number
			Connection conn = DatabaseController.getConnection();


			PreparedStatement getstmt=conn.prepareStatement("SELECT * FROM Customer WHERE PhoneNumber LIKE ?");
			getstmt.setString(1, "%"+phone+"%");
			ResultSet rs=getstmt.executeQuery();
			SQLTableEntry sl= new SQLTableEntry();
			sl.SQLRetrieve(rs);

			
			//INSERTING ONLY IF NUMBER EXIST / ACCESS TOKEN ALREADY NOT AVAILABLE
			if (sl.getAccessToken()==null) {
				if (sl.getPhoneNumber()==null) {
					Gson g = new Gson();
					String state = g.fromJson(responseData, SendOtpResponse.class).getState();
					PreparedStatement setstmt = conn.prepareStatement(
							"INSERT INTO Customer (PhoneNumber, AccessToken, State) VALUES (?, ?,?);");
					setstmt.setString(1, phone);
					setstmt.setString(2, null);
					setstmt.setString(3, state);
					setstmt.execute();
				}
				else{
					Gson g = new Gson();
					String state = g.fromJson(responseData, SendOtpResponse.class).getState();
					PreparedStatement setstmt = conn.prepareStatement(
							"UPDATE Customer SET AccessToken = ?, State = ? WHERE PhoneNumber LIKE ?;");
					setstmt.setString(1, null);
					setstmt.setString(2, state);
					setstmt.setString(3, "%"+phone+"%");
					setstmt.execute();

				}
			}


			



		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
