package com.reminder.service.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
public interface BlockWhiteDAO {

    @Insert("insert into relation_block (userId,blockId) values (#{userId},#{blockId})")
    public int block(@Param("userId")int userId,@Param("blockId")int blockId);

    @Delete("delete from relation_block where userId=#{userId} and blockId=#{blockId}")
    public int unblock(@Param("userId")int userId,@Param("blockId")int blockId);

    @Insert("insert into relation_white (userId,blockId) values (#{userId},#{whiteId})")
    public int white(@Param("userId")int userId,@Param("whiteId")int whiteId);

    @Delete("delete from relation_white where userId=#{userId} and whiteId=#{whiteId}")
    public int unwhite(@Param("userId")int userId,@Param("whiteId")int whiteId);

    @Select("select blockId from relation_block where userId=#{userId}")
    public List<Integer> getBlockList(@Param("userId")int userId);

    @Select("select whiteId from relation_white where userId=#{userId}")
    public List<Integer> getWhiteList(@Param("userId")int userId);

}
