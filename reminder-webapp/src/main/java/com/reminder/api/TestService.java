package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;

/**
 * Created by wills on 7/14/14.
 */
public interface TestService {

    @RestMethod
    public int get();

    public int noget();
}
