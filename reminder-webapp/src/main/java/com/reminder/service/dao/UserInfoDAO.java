package com.reminder.service.dao;

import com.reminder.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

/**
 * Created by wills on 7/15/14.
 */
public interface UserInfoDAO {


    @Insert("insert into user_info (name,cellphone,createTime) values" +
            " (#{name},#{cellphone},#{createTime})")
    @SelectKey(keyProperty="id",resultType=Integer.class,before=false,statement="SELECT LAST_INSERT_ID() AS id")
    public int insertUserInfo(UserInfo userInfo);


    @Select("select * from user_info where id=#{userId}")
    public UserInfo getUserInfo(int userId);

    @Select("select * from user_info where cellphone=#{cellphone}")
    public UserInfo getUserInfoByMobile(String cellphone);

}
