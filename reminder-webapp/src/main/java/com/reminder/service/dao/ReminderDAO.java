package com.reminder.service.dao;

import com.reminder.model.Reminder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wills on 7/16/14.
 */
public interface ReminderDAO {

    @Update("update reminder set status=#{status] where remindId=#{remindId}")
    public int changeStatus(@Param("remindId")long remindId,@Param("status")int status);

    @Insert("insert into reminder (userId,content,contentType,remindTime,remindType,status) values " +
            "(#{userId},#{content},#{contentType},#{remindTime},#{remindType},#{status})")
    @SelectKey(keyProperty="remindId",resultType=Long.class,before=false,statement="SELECT LAST_INSERT_ID() AS remindId")
    public long insertReminder(Reminder reminder);

    @Select("select * from reminder where userId=#{userId} and remindTime > #{now} ")
    public List<Reminder> getReminderList(@Param("userId")int userId,@Param("now")long now);

    @Select("select * from reminder where remindId=#{remindId}")
    public Reminder getReminderById(@Param("remindId")long remindId);
}
