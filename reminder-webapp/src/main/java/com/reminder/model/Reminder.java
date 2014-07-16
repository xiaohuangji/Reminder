package com.reminder.model;

import java.util.Date;

/**
 * Created by wills on 7/16/14.
 * Reminder
 */
public class Reminder {

    /**
     * 编号
     */
    public long remindId;

    /**
     * 发送者id
     */
    public int userId;

    /**
     *提醒内容
     */
    public String content;

    /**
     * 类型。文本还是语音
     */
    public int contentType;

    /**
     * 提醒时间
     */
    public long remindTime;

    /**
     * 提醒方式
     * @see com.reminder.constant.ReminderConstant
     * APP SMS CALL 多选，数值相加
     */
    public int remindType;

    /**
     * 更新时间
     * 创建为创建时间，更新为更新时间
     */
    public Date updateTime;

    /**
     * 状态
     * @see com.reminder.constant.ReminderConstant
     * S_STATUS_NORMAL正常
     * S_STATUS_CANCEL取消
     */
    public int status;

    public long getRemindId() {
        return remindId;
    }

    public void setRemindId(long remindId) {
        this.remindId = remindId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public int getRemindType() {
        return remindType;
    }

    public void setRemindType(int remindType) {
        this.remindType = remindType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
