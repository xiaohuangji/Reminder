package com.reminder.service.dao;

import com.reminder.model.UserDeviceToken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wills on 7/15/14.
 */
public interface UserDeviceTokenDAO {

    @Insert("replace into user_device_token (userId,type,token) values (#{userId},#{type},#{token})")
    public int addDevice(@Param("userId")int userId,@Param("type")int type,@Param("token")String token);

    @Delete("delete from user_device_token where userId=#{userId}")
    public int removeDevice(@Param("userId")int userId);

    @Select("select * from user_device_token where userId=#{userId}")
    public UserDeviceToken getDevice(@Param("userId")int userId);
}
