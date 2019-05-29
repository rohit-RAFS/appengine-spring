package com.example.demo;

public class SendOtpResponse {
    private String status;
    private String message;
    private String responseCode;
    private String state;

    public SendOtpResponse() {
    }

    public SendOtpResponse(String status, String message, String responseCode, String state) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
