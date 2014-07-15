package com.reminder.service.impl;

import com.reminder.api.TestService;
import com.reminder.service.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wills on 7/14/14.
 */
@Component("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDAO testDAO;

    @Override
    public int get() {
        return testDAO.get();
    }

    @Override
    public int noget() {
        return 123;
    }
}
