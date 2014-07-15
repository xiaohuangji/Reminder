package com.reminder.service.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by wills on 7/15/14.
 */
public interface UserPwdDAO {

    @Select("select password from user_pwd where userId=#{userId}")
    public String getPwd(@Param("userId")int userId);

    @Update("replace into user_pwd (userId,password) values (#{userId},#{password})")
    public int changePwd(@Param("userId")int userId,@Param("password")String password);
}
