package com.reminder.model.view;

import com.reminder.model.UserInfo;
import com.reminder.model.UserPassport;

/**
 * Created by wills on 7/15/14.
 */
public class UserInfoLoginView {

    private int code;

    private UserInfo userInfo;

    private UserPassport userPassport;

    public UserInfoLoginView(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserPassport getUserPassport() {
        return userPassport;
    }

    public void setUserPassport(UserPassport userPassport) {
        this.userPassport = userPassport;
    }
}
