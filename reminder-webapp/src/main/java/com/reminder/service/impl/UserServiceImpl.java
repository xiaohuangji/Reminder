package com.reminder.service.impl;

import com.reminder.api.UserService;
import com.reminder.model.UserInfo;
import com.reminder.service.dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public UserInfo getUserInfo(int _userId) {
        return userInfoDAO.getUserInfo(_userId);
    }

    @Override
    public int changeUserInfo(int _userId, String avatar, int birthday, int gender) {
        return 0;
    }

    @Override
    public UserInfo getUserInfoByMobile(String cellphone) {
        return userInfoDAO.getUserInfoByMobile(cellphone);
    }

    @Override
    public List<UserInfo> getUserInfos(List<Integer> userIds) {
        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        for(Integer userId:userIds){
            userInfos.add(getUserInfo(userId));
        }
        return userInfos;
    }

    @Override
    public int insertPreUserInfo(String cellphone) {
        UserInfo userInfo=new UserInfo();
        userInfo.setCellphone(cellphone);
        userInfoDAO.insertPreUserInfo(userInfo);
        return userInfo.getId();
    }
}
