package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.UserDeviceToken;

/**
 * Created by wills on 7/14/14.
 */
public interface SystemService {

    /**
     * 资源上传接口
     * @param data 文件byte
     * @param suffix  文件后缀
     * @return
     */
    @RestMethod
    public String uploadFile(byte[] data,String suffix);

    /**
     * 绑定手机设备。
     * 重复绑定需要更新；要把之前的绑定的解绑
     * 一个用户只能同时存在一个设备
     * @param _userId
     * @param deviceToken
     * @param deviceType 1表示android,2表示ios
     * @return
     */
    @RestMethod
    public int bindDevice(int _userId,String deviceToken,int deviceType);

    /**
     * 解绑设备
     * @param _userId
     * @return
     */
    @RestMethod
    public int unbindDevice(int _userId);

    /**
     * 获取用户device_token
     * @param _userId
     * @return
     */
    public UserDeviceToken getDevice(int _userId);
}
