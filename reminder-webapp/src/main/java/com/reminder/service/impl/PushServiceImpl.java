package com.reminder.service.impl;

import com.reminder.api.PushService;
import com.reminder.api.SystemService;
import com.reminder.constant.ResultConstant;
import com.reminder.model.MessageInfo;
import com.reminder.model.UserDeviceToken;
import com.reminder.service.push.PushClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wills on 7/15/14.
 */
@Component("pushService")
public class PushServiceImpl implements PushService {

    @Autowired
    private SystemService systemService;

    @Override
    public int pushMessage(int _userId, MessageInfo message) {
        UserDeviceToken userDeviceToken=systemService.getDevice(_userId);
        if(userDeviceToken==null)
            return ResultConstant.OP_FAIL;
        return PushClientFactory.push(userDeviceToken, message);
    }
}
