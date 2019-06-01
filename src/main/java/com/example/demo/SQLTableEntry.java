package com.example.demo;

import com.example.demo.Model.DatabaseController;
import com.mysql.cj.protocol.Resultset;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class SQLTableEntry {

    String PhoneNumber;
    String AccessToken;
    String State;
    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());


    public SQLTableEntry() {
    }

    public SQLTableEntry(String phoneNumber, String accessToken, String state) {
        PhoneNumber = phoneNumber;
        AccessToken = accessToken;
        State = state;
    }

    public void SQLRetrieve(ResultSet rs){

        try {
            // iterate through the java resultset
            while (rs.next()) {
                PhoneNumber = rs.getString(1);
                AccessToken = rs.getString(2);
                State = rs.getString(3);
                LOGGER.info("phonenumberrequest"+PhoneNumber);
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
