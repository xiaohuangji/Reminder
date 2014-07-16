package com.reminder.model;

import java.util.Date;

/**
 * Created by wills on 7/16/14.
 * 由Reminder触发的Event
 * 收件箱的概念
 */
public class EventReminder {

    public long id;

    /**
     * 接收提醒人userId
     */
    public int userId;

    /**
     * 链接到的reminderId
     */
    public long remindId;

    /**
     * 自定义提醒时间
     */
    public long remindTime;

    /**
     * 自定义提醒方式
     * @see com.reminder.constant.ReminderConstant
     * APP SMS CALL 多选，数值相加
     */
    public int remindType;

    /**
     * 更新时间
     */
    public Date updateTime;

    /**
     * 状态
     * @see com.reminder.constant.ReminderConstant
     * R_STATUS_RECEIVE  接收
     * R_STATUS_IGNORE   忽略
     * R_STATUS_DELETE   删除
     */
    public int status;

    /**
     * 类型
     * @see com.reminder.constant.ReminderConstant
     * R_EVENT_ORIGIN 原始类型
     * R_EVENT_CUSTOM  自定义类型
     */
    public int eventType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getRemindId() {
        return remindId;
    }

    public void setRemindId(long remindId) {
        this.remindId = remindId;
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

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
