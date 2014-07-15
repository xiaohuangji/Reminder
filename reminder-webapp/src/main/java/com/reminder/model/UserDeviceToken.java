package com.reminder.model;

/**
 * Created by wills on 7/15/14.
 */
public class UserDeviceToken {

    private int userId;

    private String token;

    private int type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


