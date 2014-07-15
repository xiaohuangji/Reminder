package com.reminder.service.impl;

import com.reminder.api.UserService;
import com.reminder.model.UserInfo;
import org.springframework.stereotype.Component;

/**
 * Created by wills on 7/15/14.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Override
    public UserInfo getUserInfo(int _userId) {
        return null;
    }

    @Override
    public int changeUserInfo(int _userId, String avatar, int birthday, int gender) {
        return 0;
    }
}
