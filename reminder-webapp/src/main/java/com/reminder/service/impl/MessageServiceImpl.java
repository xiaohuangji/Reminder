package com.reminder.service.impl;

import com.reminder.api.MessageService;
import com.reminder.model.MessageInfo;
import com.reminder.model.view.MessageInfoView;
import com.reminder.service.dao.MessageBoxDAO;
import com.reminder.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
@Component("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageBoxDAO messageBoxDAO;

    @Override
    public List<MessageInfoView> getMessage(int _userId, int pageNo, int pageSize) {
        List<MessageInfo> messageInfos=messageBoxDAO.getMessage(_userId,pageNo*pageSize,pageSize);
        List<MessageInfoView> messageInfoViews=new ArrayList<MessageInfoView>();
        if(messageInfos==null){
            return messageInfoViews;
        }
        for(MessageInfo messageInfo:messageInfos){
            MessageInfoView view=new MessageInfoView();
            view.setMessageInfo(messageInfo);
            //add view info about user
            view.setFromHeadUrl("");
            view.setFromName("");
            //--
            messageInfoViews.add(view);
        }
        return messageInfoViews;
    }

    @Override
    public int sendMessage(int fromId, int toId, int type, Object payload) {
        MessageInfo messageInfo=new MessageInfo(fromId,toId,type, JsonUtil.toJson(payload));
        return messageBoxDAO.insertMessage(messageInfo);
    }
}
