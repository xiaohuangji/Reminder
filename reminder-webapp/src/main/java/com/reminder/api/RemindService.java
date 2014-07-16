package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.view.ReminderSimpleView;

import java.util.List;

/**
 * Created by wills on 7/14/14.
 */
public interface RemindService {

    @RestMethod
    List<ReminderSimpleView> getRemindList(int _userId);

    //发送方接口======================================
    /**
     *
     * @param _userId
     * @param content
     * @param contentType
     * @param remindTime
     * @param remindType
     * @return
     */
    @RestMethod
    public int create(int _userId,String content,int contentType,long remindTime,int remindType,String cellphones);

    /**
     * 发送发取消提醒
     * @param remindId
     * @return
     */
    @RestMethod
    public int cancel(long remindId);


    //接收方接口====================================
    /**
     * 接受提醒
     * @param _userId
     * @param remindId
     * @return
     */
    @RestMethod
    public int receive(int _userId,long remindId);

    /**
     * 忽略提醒
     * @param _userId
     * @param remindId
     * @return
     */
    @RestMethod
    public int ignore(int _userId,long remindId);

    /**
     * 删除提醒
     * @param _userId
     * @param remindId
     * @return
     */
    @RestMethod
    public int delete(int _userId,long remindId);

    /**
     * 接收方修改提醒
     * @param _userId
     * @param remindId
     * @param remindTime
     * @param remindType
     * @return
     */
    @RestMethod
    public int modify(int _userId,long remindId,long remindTime,int remindType);
}
