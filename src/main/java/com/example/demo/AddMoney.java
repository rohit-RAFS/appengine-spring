package com.example.demo;
import com.paytm.pg.merchant.CheckSumServiceHelper;

import java.net.URL;
import java.util.Map;
import java.util.TreeMap;


public class AddMoney {
    public AddMoney(){}

    String merchantMid = "merchant-perpule-stg";
    // Key in your staging and production MID available in your dashboard
    String merchantKey = "jlgBKygCagp6PKECMEbLBfCviJorleAj";
    // Key in your staging and production merchant key available in your dashboard
    String orderId = "order1";
    String sso_token = "4ff34c02-1a5c-43f8-95fc-091d53b01700";
    String channelId = "WEB";
    String custId = "1000039359";
    String mobileNo = "7397430279";
    String email = "amba.rohit8@gmail.com";
    String txnAmount = "1.00";
    String website = "WEBSTAGING";
    // This is the staging value. Production value is available in your dashboard
    String industryTypeId = "Retail";
    // This is the staging value. Production value is available in your dashboard
    String callbackUrl = "localhost:8080/addmoney";
    public void add_money() throws Exception {
        TreeMap<String, String> paytmParams = new TreeMap<String, String>();
        paytmParams.put("MID", merchantMid);
        paytmParams.put("REQUEST_TYPE", "ADD_MONEY");
        paytmParams.put("SSO_TOKEN", sso_token);
        paytmParams.put("ORDER_ID", orderId);
        paytmParams.put("CHANNEL_ID", channelId);
        paytmParams.put("CUST_ID", custId);
        paytmParams.put("MOBILE_NO", mobileNo);
        paytmParams.put("EMAIL", email);
        paytmParams.put("TXN_AMOUNT", txnAmount);
        paytmParams.put("WEBSITE", website);
        paytmParams.put("INDUSTRY_TYPE_ID", industryTypeId);
        paytmParams.put("CALLBACK_URL", callbackUrl);
        String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, paytmParams);
        StringBuilder outputHtml = new StringBuilder();
        outputHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
        outputHtml.append("<html>");
        outputHtml.append("<head>");
        outputHtml.append("<title>Merchant Checkout Page</title>");
        outputHtml.append("</head>");
        outputHtml.append("<body>");
        outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
        URL transactionURL = new URL("https://securegw-stage.paytm.in/theia/processTransaction");
        outputHtml.append("<form method='post' action='" + transactionURL + "' name='f1'>");
        for (Map.Entry<String, String> entry : paytmParams.entrySet()) {
            outputHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'>");
        }
        outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='" + paytmChecksum + "'>");
        outputHtml.append("</form>");
        outputHtml.append("<script type='text/javascript'>");
        outputHtml.append("document.f1.submit();");
        outputHtml.append("</script>");
        outputHtml.append("</body>");
        outputHtml.append("</html>");
    }
}
