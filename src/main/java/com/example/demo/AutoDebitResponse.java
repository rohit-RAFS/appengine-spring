package com.example.demo;

public class AutoDebitResponse {
    String TxnId;
    String MID;
    String orderId;
    String TxnAmount;
    String BankTxnId;
    String ResponseCode;
    String ResponseMessage;
    String Status;
    String PaymentMode;
    String BankName;
    String CheckSum;
    String CustId;


    String MBID;
    public AutoDebitResponse(String txnId, String MID, String orderId, String txnAmount, String bankTxnId, String responseCode, String responseMessage, String status, String paymentMode, String bankName, String checkSum, String custId, String MBID) {
        this.TxnId = txnId;
        this.MID = MID;
        this.orderId = orderId;
        this.TxnAmount = txnAmount;
        this.BankTxnId = bankTxnId;
        this.ResponseCode = responseCode;
        this.ResponseMessage = responseMessage;
        this.Status = status;
        this.PaymentMode = paymentMode;
        this.BankName = bankName;
        this.CheckSum = checkSum;
        this.CustId = custId;
        this.MBID = MBID;
    }

    public String getTxnId() {
        return TxnId;
    }

    public void setTxnId(String txnId) {
        TxnId = txnId;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnAmount() {
        return TxnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        TxnAmount = txnAmount;
    }

    public String getBankTxnId() {
        return BankTxnId;
    }

    public void setBankTxnId(String bankTxnId) {
        BankTxnId = bankTxnId;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getCheckSum() {
        return CheckSum;
    }

    public void setCheckSum(String checkSum) {
        CheckSum = checkSum;
    }

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public String getMBID() {
        return MBID;
    }

    public void setMBID(String MBID) {
        this.MBID = MBID;
    }





}
