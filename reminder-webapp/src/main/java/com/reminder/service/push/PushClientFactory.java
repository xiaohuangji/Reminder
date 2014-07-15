package com.reminder.service.push;

import com.reminder.constant.DeviceConstant;
import com.reminder.constant.ResultConstant;
import com.reminder.model.MessageInfo;
import com.reminder.model.UserDeviceToken;

public class PushClientFactory {

	public static int push(UserDeviceToken userDevice ,MessageInfo message){
		PushClient  pushClient=null;
		if(userDevice.getType()== DeviceConstant.ANDROID){
			pushClient=AndroidPushClient.getInstance();
		}else if(userDevice.getType()==DeviceConstant.IOS){
			pushClient=ApplePushClient.getInstance();
		}else{
			return ResultConstant.OP_FAIL;
		}
		return pushClient.push(userDevice, message);
	}
}
