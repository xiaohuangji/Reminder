package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.UserInfo;

import java.util.List;

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
     * 指获取userInfo
     * @param userIds
     * @return
     */
    public List<UserInfo> getUserInfos(List<Integer> userIds);

    /**
     * 修改头像
     * @param _userId
     * @param avatar
     * @return
     */
    @RestMethod
    public int changeUserInfo(int _userId,String avatar,int birthday,int gender);

    /**
     * 通过手机号获取用户信息
     * @param cellphone
     * @return
     */
    public UserInfo getUserInfoByMobile(String cellphone);

    /**
     * 预生成用户
     * @param cellphone
     * @return 生成的userId
     */
    public int insertPreUserInfo(String cellphone);

}
