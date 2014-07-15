package com.reminder.api;

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
    public UserInfo getUserInfo(int _userId);

    /**
     * 修改头像
     * @param _userId
     * @param avatar
     * @return
     */
    public int changeUserInfo(int _userId,String avatar,int birthday,int gender);

}
