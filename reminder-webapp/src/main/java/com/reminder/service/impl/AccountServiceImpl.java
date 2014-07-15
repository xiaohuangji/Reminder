package com.reminder.service.impl;

import com.reminder.api.AccountService;
import com.reminder.model.view.UserInfoLoginView;
import org.springframework.stereotype.Component;

/**
 * Created by wills on 7/15/14.
 */
@Component("accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public UserInfoLoginView login(String cellphone, String password) {
        return null;
    }

    @Override
    public UserInfoLoginView register(String name, String cellphone, String password, String validationCode) {
        return null;
    }

    @Override
    public int sendValidationCode(String cellphone) {
        return 0;
    }

    @Override
    public int changePassword(int _userId, String oldPassword, String newPassword) {
        return 0;
    }
}
