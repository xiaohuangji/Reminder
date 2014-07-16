package com.reminder.service.dao;

import com.reminder.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * Created by wills on 7/15/14.
 */
public interface UserInfoDAO {

    /**
     * @see com.reminder.constant.AccountConstant
     * status
     * @param userInfo
     * @return
     */

    @Insert("insert into user_info (name,cellphone,createTime,status) values" +
            " (#{name},#{cellphone},#{createTime},1)")
    @SelectKey(keyProperty="id",resultType=Integer.class,before=false,statement="SELECT LAST_INSERT_ID() AS id")
    public int insertUserInfo(UserInfo userInfo);

    @Insert("insert into user_info (cellphone,status) values" +
            " (#{cellphone},0)")
    @SelectKey(keyProperty="id",resultType=Integer.class,before=false,statement="SELECT LAST_INSERT_ID() AS id")
    public int insertPreUserInfo(UserInfo userInfo);


    /**
     * 激活用户，之前预生成的用户注册为正式用户
     * @param name
     * @return
     */
    @Update("update user_info set name=#{name},status=1,createTime=#{createTime} where id=#{userId} and status=0")
    public int activeUser(@Param("name")String name,@Param("userId")int userId,@Param("createTime")Date createTime);
    /**
     * status＝1，只取正常的用户
     * @param userId
     * @return
     */
    @Select("select * from user_info where id=#{userId} and status=1")
    public UserInfo getUserInfo(@Param("userId")int userId);

    /**
     * 取所有的用户
     * @param cellphone
     * @return
     */
    @Select("select * from user_info where cellphone=#{cellphone}")
    public UserInfo getUserInfoByMobile(@Param("cellphone")String cellphone);

    /**
     * 根据手机号获取用户状态
     * @param cellphone
     * @return
     */
    @Select("select status from user_info where cellphone=#{cellphone}")
    public Integer getStatusByMobile(@Param("cellphone")String cellphone);

}
