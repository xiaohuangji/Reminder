package com.reminder.service.impl;

import com.reminder.api.SystemService;
import com.reminder.constant.ResultConstant;
import com.reminder.model.UserDeviceToken;
import com.reminder.service.dao.UserDeviceTokenDAO;
import com.reminder.service.fs.BCSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

/**
 * Created by wills on 7/15/14.
 */
@Component("systemService")
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserDeviceTokenDAO userDeviceTokenDAO;

    @Override
    public String uploadFile(byte[] data, String suffix) {
        String fileName= BCSClient.putObject(new ByteArrayInputStream(data), data.length, suffix);
        return BCSClient.fileUrl(fileName);
    }

    @Override
    public int bindDevice(int _userId, String deviceToken, int deviceType) {
        int result=userDeviceTokenDAO.addDevice(_userId, deviceType, deviceToken);
        return result>0? ResultConstant.OP_SUCC:ResultConstant.OP_FAIL;
    }

    @Override
    public int unbindDevice(int _userId) {
        userDeviceTokenDAO.removeDevice(_userId);
        return ResultConstant.OP_SUCC;
    }

    @Override
    public UserDeviceToken getDevice(int _userId) {
        return userDeviceTokenDAO.getDevice(_userId);
    }
}
