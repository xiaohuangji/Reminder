package com.reminder.service.push;

import com.reminder.model.MessageInfo;
import com.reminder.model.UserDeviceToken;

public interface PushClient {

	public int push(UserDeviceToken userDevice, MessageInfo message);
}
