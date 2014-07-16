package com.reminder.model.view;

import com.reminder.model.UserInfo;
import com.reminder.model.UserPassport;

/**
 * Created by wills on 7/15/14.
 */
public class UserInfoLoginView {

    private int loginCode;

    private UserInfo userInfo;

    private UserPassport userPassport;

    public int getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(int loginCode) {
        this.loginCode = loginCode;
    }

    public UserInfoLoginView(int loginCode) {
        this.loginCode = loginCode;
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
