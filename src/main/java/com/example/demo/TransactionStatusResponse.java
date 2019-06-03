package com.example.demo;

public class TransactionStatusResponse {
    String TXNID;
    String BANKTXNID;
    String ORDERID;
    String TXNAMOUNT;
    String STATUS;
    String TXNTYPE;
    String GATEWAYNAME;
    String RESPCODE;
    String RESPMSG;
    String BANKNAME;
    String MID;
    String PAYMENTMODE;
    String REFUNDAMT;
    String TXNDATE;


    public TransactionStatusResponse(String TXNID, String BANKTXNID, String ORDERID, String TXNAMOUNT, String STATUS, String TXNTYPE, String GATEWAYNAME, String RESPCODE, String RESPMSG, String BANKNAME, String MID, String PAYMENTMODE, String REFUNDAMT, String TXNDATE) {
        this.TXNID = TXNID;
        this.BANKTXNID = BANKTXNID;
        this.ORDERID = ORDERID;
        this.TXNAMOUNT = TXNAMOUNT;
        this.STATUS = STATUS;
        this.TXNTYPE = TXNTYPE;
        this.GATEWAYNAME = GATEWAYNAME;
        this.RESPCODE = RESPCODE;
        this.RESPMSG = RESPMSG;
        this.BANKNAME = BANKNAME;
        this.MID = MID;
        this.PAYMENTMODE = PAYMENTMODE;
        this.REFUNDAMT = REFUNDAMT;
        this.TXNDATE = TXNDATE;
    }


    public String getTXNID() {
        return TXNID;
    }

    public void setTXNID(String TXNID) {
        this.TXNID = TXNID;
    }

    public String getBANKTXNID() {
        return BANKTXNID;
    }

    public void setBANKTXNID(String BANKTXNID) {
        this.BANKTXNID = BANKTXNID;
    }

    public String getORDERID() {
        return ORDERID;
    }

    public void setORDERID(String ORDERID) {
        this.ORDERID = ORDERID;
    }

    public String getTXNAMOUNT() {
        return TXNAMOUNT;
    }

    public void setTXNAMOUNT(String TXNAMOUNT) {
        this.TXNAMOUNT = TXNAMOUNT;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTXNTYPE() {
        return TXNTYPE;
    }

    public void setTXNTYPE(String TXNTYPE) {
        this.TXNTYPE = TXNTYPE;
    }

    public String getGATEWAYNAME() {
        return GATEWAYNAME;
    }

    public void setGATEWAYNAME(String GATEWAYNAME) {
        this.GATEWAYNAME = GATEWAYNAME;
    }

    public String getRESPCODE() {
        return RESPCODE;
    }

    public void setRESPCODE(String RESPCODE) {
        this.RESPCODE = RESPCODE;
    }

    public String getRESPMSG() {
        return RESPMSG;
    }

    public void setRESPMSG(String RESPMSG) {
        this.RESPMSG = RESPMSG;
    }

    public String getBANKNAME() {
        return BANKNAME;
    }

    public void setBANKNAME(String BANKNAME) {
        this.BANKNAME = BANKNAME;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getPAYMENTMODE() {
        return PAYMENTMODE;
    }

    public void setPAYMENTMODE(String PAYMENTMODE) {
        this.PAYMENTMODE = PAYMENTMODE;
    }

    public String getREFUNDAMT() {
        return REFUNDAMT;
    }

    public void setREFUNDAMT(String REFUNDAMT) {
        this.REFUNDAMT = REFUNDAMT;
    }

    public String getTXNDATE() {
        return TXNDATE;
    }

    public void setTXNDATE(String TXNDATE) {
        this.TXNDATE = TXNDATE;
    }

}


