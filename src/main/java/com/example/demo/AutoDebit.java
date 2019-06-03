package com.example.demo;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class AutoDebit {
// for production
// URL transactionURL = new URL("https://securegw.paytm.in/order/directPay");

    String merchantKey = "&!vj74@Ri&g6U1TI";
    String number;
    String txn;

    public AutoDebit(String number, String txn) {
        this.number = number;
        this.txn = txn;
    }
    String responseData;

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }




public void auto_debit() {
    TreeMap<String, String> paytmParams = new TreeMap<String, String>();
    paytmParams.put("ReqType", "WITHDRAW");
// paytmParams.put("PREAUTH_ID", ""); // only in case of capture
    paytmParams.put("SSOToken", "31add395-f16b-4dc0-b066-9139b5821700");
    paytmParams.put("MID", "Delvit07224170213556");
    paytmParams.put("TxnAmount", txn);
    paytmParams.put("AppIP", "192.168.1.72");
    paytmParams.put("OrderId", "ORDER_123456789");
    paytmParams.put("Currency", "INR");
    paytmParams.put("DeviceId", number);
    paytmParams.put("PaymentMode", "PPI");
    paytmParams.put("CustId", "1000039359");
    paytmParams.put("IndustryType", "Retail");
    paytmParams.put("Channel", "WAP");
    paytmParams.put("AuthMode", "USRPWD");


    try {
        URL transactionURL = new URL("https://securegw-stage.paytm.in/order/directPay");


        String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, paytmParams);
        paytmParams.put("CheckSum", paytmChecksum);

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
       // return responseData;
    } catch (Exception exception) {
        exception.printStackTrace();
    }
}

}
