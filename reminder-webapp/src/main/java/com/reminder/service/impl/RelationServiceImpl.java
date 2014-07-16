package com.reminder.service.impl;

import com.reminder.api.RelationService;
import com.reminder.api.UserService;
import com.reminder.model.UserInfo;
import com.reminder.service.dao.BlockWhiteDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.reminder.constant.ResultConstant.OP_SUCC;

/**
 * Created by wills on 7/16/14.
 */
@Component("relationService")
public class RelationServiceImpl implements RelationService {

    private Logger logger=Logger.getLogger(RelationServiceImpl.class);

    @Autowired
    private BlockWhiteDAO blockWhiteDAO;

    @Autowired
    private UserService userService;

    @Override
    public int block(int _userId, int blockId) {
        if(_userId==blockId){
            return OP_SUCC;
        }
        try{
            blockWhiteDAO.block(_userId, blockId);
        }catch(Exception e){
            logger.warn("block error,",e);
        }
        return OP_SUCC;
    }

    @Override
    public int unblock(int _userId, int blockId) {
        blockWhiteDAO.unblock(_userId, blockId);
        return OP_SUCC;
    }

    @Override
    public int white(int _userId, int whiteId) {
        if(_userId==whiteId){
            return OP_SUCC;
        }
        try{
            blockWhiteDAO.white(_userId, whiteId);
        }catch(Exception e){
            logger.warn("white error,",e);
        }
        return OP_SUCC;
    }

    @Override
    public int unwhite(int _userId, int whiteId) {
        blockWhiteDAO.unwhite(_userId, whiteId);
        return OP_SUCC;
    }

    @Override
    public List<UserInfo> getBlockList(int _userId) {
        List<Integer> userIds=blockWhiteDAO.getBlockList(_userId);
        return userService.getUserInfos(userIds);
    }

    @Override
    public List<UserInfo> getWhiteList(int _userId) {
        List<Integer> userIds=blockWhiteDAO.getWhiteList(_userId);
        return userService.getUserInfos(userIds);
    }

    @Override
    public int uploadContacts(int _userId, String contacts) {
        return 0;
    }
}
