package com.example.demo;

public class ValidateTokenResponse {

    private String id;
    private String email;
    private String mobile;

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    private String expires;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getExpires() {
        return expires;
    }



    public ValidateTokenResponse(String id, String email, String mobile, String expires) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
        this.expires = expires;
    }


}
