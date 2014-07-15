package com.reminder.service.dao;

import org.apache.ibatis.annotations.Select;

/**
 * Created by wills on 7/14/14.
 */
public interface TestDAO {

    @Select("select id from test limit 1")
    public int get();
}
