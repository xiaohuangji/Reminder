package com.reminder.api;

import com.reminder.model.MessageInfo;

/**
 * Created by wills on 7/15/14.
 */
public interface PushService {

    /**
     * 根据userId拿出设备,然后发送
     * @param _userId
     * @param message
     * @return
     */
    public int pushMessage(int _userId,MessageInfo message);
}
