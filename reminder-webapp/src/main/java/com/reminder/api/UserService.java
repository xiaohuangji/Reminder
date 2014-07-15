package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.UserInfo;

/**
 * Created by wills on 7/14/14.
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param _userId
     * @return
     */
    @RestMethod
    public UserInfo getUserInfo(int _userId);

    /**
     * 修改头像
     * @param _userId
     * @param avatar
     * @return
     */
    @RestMethod
    public int changeUserInfo(int _userId,String avatar,int birthday,int gender);

    /**
     * 通过
     * @param cellphone
     * @return
     */
    public UserInfo getUserInfoByMobile(String cellphone);

}
