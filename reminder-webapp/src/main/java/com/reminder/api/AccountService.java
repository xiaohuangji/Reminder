package com.reminder.api;

import com.reminder.mcp.annotation.NoTicket;
import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.view.UserInfoLoginView;

/**
 * Created by wills on 7/14/14.
 */
public interface AccountService {

    /**
     * 登录
     * @param cellphone
     * @param password
     * @return
     */
    @RestMethod
    @NoTicket
    public UserInfoLoginView login(String cellphone,String password);

    /**
     * 注册
     * @param cellphone
     * @param password
     * @param validationCode
     * @return
     */
    @RestMethod
    @NoTicket
    public UserInfoLoginView register(String name,String cellphone,String password,String validationCode);

    /**
     * 获取验证码，系统将下发验证码到手机
     * @param cellphone
     */
    @RestMethod
    @NoTicket
    public int sendValidationCode(String cellphone);

    /**
     * 修改密码
     * @param _userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RestMethod
    public int changePassword(int _userId,String oldPassword,String newPassword);
}
