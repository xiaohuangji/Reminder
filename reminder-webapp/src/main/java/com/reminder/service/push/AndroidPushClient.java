package com.reminder.service.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.reminder.constant.BaiduConstant;
import com.reminder.constant.ResultConstant;
import com.reminder.model.MessageInfo;
import com.reminder.model.UserDeviceToken;
import com.reminder.util.JsonUtil;

public class AndroidPushClient implements PushClient {

	private static AndroidPushClient pushClient = null;

	public static AndroidPushClient getInstance() {
		if (pushClient == null) {
			pushClient = new AndroidPushClient();
		}
		return pushClient;
	}

	private static int ANDROID_DEVICE_TYPE = 3;

	private static String ANDROID_TOKEN_SPLITSTR = "\\@\\#\\$";

	private BaiduChannelClient channelClient = null;

	private AndroidPushClient() {
		channelClient = new BaiduChannelClient(new ChannelKeyPair(BaiduConstant.BAIDUPUSH_API_KEY,
				BaiduConstant.BAIDUPUSH_SECRET_KEY));
	}

	@Override
	public int push(UserDeviceToken userDevice, MessageInfo message) {
		PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		request.setDeviceType(ANDROID_DEVICE_TYPE);
		BaiduChannel channel = new BaiduChannel(userDevice);

		request.setChannelId(channel.channelId);
		request.setUserId(channel.baiduUserId);

		// 组装消息格式,将message转成json
		request.setMessage(JsonUtil.toJson(message));

		PushUnicastMessageResponse response = null;

		try {
			response = channelClient.pushUnicastMessage(request);
			int result = response.getSuccessAmount();
			if (result == 1) {
				return ResultConstant.OP_SUCC;
			}
		} catch (ChannelClientException e) {
			e.printStackTrace();
		} catch (ChannelServerException e) {
			e.printStackTrace();
		}

		return ResultConstant.OP_FAIL;
	}

	/**
	 * baidupush单播推送需要的标记 从客户端上传的token中解析而来 此userId与系统中userId是两回事
	 * 
	 * @author
	 * 
	 */
	class BaiduChannel {

		public long channelId;
		public String baiduUserId;

		public BaiduChannel(UserDeviceToken userDeviceToken) {
			String token = userDeviceToken.getToken();
			String[] ts = token.split(ANDROID_TOKEN_SPLITSTR);
			channelId = Long.valueOf(ts[0]);
			baiduUserId = ts[1];
		}
	}
}
