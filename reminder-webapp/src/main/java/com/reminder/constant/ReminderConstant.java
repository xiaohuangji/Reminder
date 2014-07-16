package com.reminder.constant;

/**
 * Created by wills on 7/14/14.
 */
public class ReminderConstant {

    // 发送者事件状态
    public static final int S_STATUS_NORMAL=1;

    public static final int S_STATUS_CANCEL=2;

    //提醒内容类型
    public static final int S_CONTENT_TEXT=1;

    public static final int S_CONTENT_AUDIO=2;


    //接收者事件状态
    public static final int R_STATUS_PENDING=10;

    public static final int R_STATUS_RECEIVE=11;

    public static final int R_STATUS_IGNORE=12;

    public static final int R_STATUS_DELETE=13;


    public static final int R_EVENT_ORIGIN=0;

    public static final int R_EVENT_CUSTOM=1;

    //接收or发起
    public static final int REMINDER_SEND=1;

    public static final int REMINDER_RECEIVE=2;

    //提醒类型
    public static final int E_TYPE_APP=1;

    public static final int E_TYPE_SMS=2;

    public static final int E_TYPE_CALL=4;



}
