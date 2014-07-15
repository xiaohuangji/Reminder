package com.reminder.model.view;

import com.reminder.model.MessageInfo;

/**
 * Created by wills on 7/15/14.
 */
public class MessageInfoView {

    private MessageInfo messageInfo;

    private String fromName;

    private String fromHeadUrl;


    public MessageInfo getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromHeadUrl() {
        return fromHeadUrl;
    }

    public void setFromHeadUrl(String fromHeadUrl) {
        this.fromHeadUrl = fromHeadUrl;
    }
}
