package com.reminder.service.dao;

import com.reminder.model.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
public interface MessageBoxDAO {

    @Insert("insert into message_box (fromId,toId,type,payload) values (#{fromId},#{toId},#{type},#{payload})")
    public int insertMessage(MessageInfo messageInfo);

    @Select("select * from message_box where toId=#{toId} order by id desc limit #{start},#{rows}")
    public List<MessageInfo> getMessage(@Param("toId")int toId,@Param("start")int start,@Param("rows")int rows);
}
