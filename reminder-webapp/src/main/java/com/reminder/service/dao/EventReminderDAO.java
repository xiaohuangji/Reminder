package com.reminder.service.dao;

import com.reminder.model.EventReminder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by wills on 7/16/14.
 */
public interface EventReminderDAO {

    /**
     * eventTYPE=0 表示初始时为原始类型，非自定义类型
     * STATUS=10表示初始为待处理状态

     * @param eventReminder
     * @return
     */
    @Insert("insert into event_reminder (userId,remindId,remindTime,remindType,eventType,status) values (" +
            "#{userId},#{remindId},#{remindTime},#{remindType},#{eventType},#{status})")
    public int insertEventReminder(EventReminder eventReminder);

    @Update("update event_reminder set status=#{status} where userId=#{userId} and remindId=#{remindId}")
    public int changeStatus(@Param("userId")int userId,@Param("remindId")long remindId,@Param("status")int status);

    @Select("select * from event_reminder where userId=#{userId} and remindTime > #{now}")
    public List<EventReminder> getEventReminderList(@Param("userId")int userId,@Param("now")long now);

    @Select("select * from event_reminder where userId=#{userId} and remindId=#{remindId}")
    public EventReminder getEventReminderById(@Param("userId")int userId,@Param("remindId")long remindId);
}
