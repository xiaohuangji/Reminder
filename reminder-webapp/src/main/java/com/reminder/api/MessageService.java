package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.view.MessageInfoView;

import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
public interface MessageService {

    /**
     * 获取消息
     * @param _userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RestMethod
    public List<MessageInfoView> getMessage(int _userId,int pageNo,int pageSize);

    /**
     * 发送消息
     * @param fromId
     * @param toId
     * @param payload 根据type不同，有效负载也不同。可能是String,也可能是long等
     * @param type
     * @return
     */
    public int sendMessage(int fromId,int toId,int type ,Object payload);

}
