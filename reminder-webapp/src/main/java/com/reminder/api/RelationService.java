package com.reminder.api;

import com.reminder.mcp.annotation.RestMethod;
import com.reminder.model.UserInfo;

import java.util.List;

/**
 * Created by wills on 7/15/14.
 */
public interface RelationService {

    @RestMethod
    public int block(int _userId,int blockId);

    @RestMethod
    public int unblock(int _userId,int blockId);

    @RestMethod
    public int white(int _userId,int whiteId);

    @RestMethod
    public int unwhite(int _userId,int whiteId);

    @RestMethod
    public List<UserInfo> getBlockList(int _userId);

    @RestMethod
    public List<UserInfo> getWhiteList(int _userId);

    @RestMethod
    public int uploadContacts(int _userId,String contacts);
}
