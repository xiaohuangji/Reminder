package com.reminder.service.impl;

import com.reminder.api.RemindService;
import com.reminder.api.UserService;
import com.reminder.constant.ResultConstant;
import com.reminder.model.EventReminder;
import com.reminder.model.Reminder;
import com.reminder.model.UserInfo;
import com.reminder.model.view.ReminderSimpleView;
import com.reminder.service.dao.EventReminderDAO;
import com.reminder.service.dao.ReminderDAO;
import com.reminder.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.reminder.constant.ReminderConstant.*;

/**
 * Created by wills on 7/15/14.
 */
@Component("remindService")
public class RemindServiceImpl implements RemindService {

    private final static Logger logger=Logger.getLogger(RemindServiceImpl.class);

    @Autowired
    private ReminderDAO reminderDAO;

    @Autowired
    private EventReminderDAO eventReminderDAO;

    @Autowired
    private UserService userService;

    @Override
    public int create(int _userId, String content, int contentType, long remindTime, int remindType,String cellphones) {
        //验证余额

        //插入reminder
        Reminder reminder=new Reminder();
        reminder.setUserId(_userId);
        reminder.setContent(content);
        reminder.setContentType(contentType);
        reminder.setRemindTime(remindTime);
        reminder.setRemindType(remindType);
        reminder.setStatus(S_STATUS_NORMAL);
        reminderDAO.insertReminder(reminder);

        long remindId=reminder.getRemindId();
        logger.info("create reminder succ, userId:"+_userId+" remindId:"+remindId);

        //分发eventReminder
        List<String> cellphoneList= JsonUtil.fromJson(cellphones,List.class);
        for(String cellphone:cellphoneList){
            UserInfo userInfo=userService.getUserInfoByMobile(cellphone);
            int userId;
            if(userInfo==null){
                //手机号不存在，预生成
                userId=userService.insertPreUserInfo(cellphone);
            }else{
                userId=userInfo.getId();
            }
            //向此用户分发eventReminder
            EventReminder eventReminder=new EventReminder();
            eventReminder.setUserId(userId);
            eventReminder.setRemindId(remindId);
            eventReminder.setRemindTime(remindTime);
            eventReminder.setRemindType(remindType);
            eventReminder.setEventType(R_EVENT_ORIGIN);
            eventReminder.setStatus(R_STATUS_PENDING);
            eventReminderDAO.insertEventReminder(eventReminder);
            logger.info("create event reminder succ, userId:"+userId+" cellphone:"+cellphone);
        }

        return ResultConstant.OP_SUCC;
    }

    @Override
    public int cancel(long remindId) {
        return reminderDAO.changeStatus(remindId,S_STATUS_CANCEL)>0? ResultConstant.OP_SUCC:ResultConstant.OP_FAIL;
    }

    @Override
    public int receive(int _userId, long remindId) {
        eventReminderDAO.changeStatus(_userId,remindId,R_STATUS_RECEIVE);
        return ResultConstant.OP_SUCC;
    }

    @Override
    public int ignore(int _userId, long remindId) {
        eventReminderDAO.changeStatus(_userId,remindId,R_STATUS_IGNORE);
        return ResultConstant.OP_SUCC;
    }

    @Override
    public int delete(int _userId, long remindId) {
        eventReminderDAO.changeStatus(_userId,remindId,R_STATUS_DELETE);
        return ResultConstant.OP_SUCC;
    }

    @Override
    public int modify(int _userId, long remindId, long remindTime, int remindType) {
        return 0;
    }

    @Override
    public List<ReminderSimpleView> getRemindList(int _userId) {
        List<Reminder> reminders=reminderDAO.getReminderList(_userId,System.currentTimeMillis());
        List<EventReminder> eventReminders=eventReminderDAO.getEventReminderList(_userId, System.currentTimeMillis());
        List<ReminderSimpleView> reminderSimpleViews=new ArrayList<ReminderSimpleView>();
        for(Reminder reminder:reminders){
            ReminderSimpleView simpleView=new ReminderSimpleView();
            simpleView.setUserId(reminder.getUserId());
            simpleView.setRemindId(reminder.getRemindId());
            simpleView.setContent(reminder.getContent());
            simpleView.setContentType(reminder.getContentType());
            simpleView.setRemindTime(reminder.getRemindTime());
            simpleView.setRemindType(reminder.getRemindType());
            simpleView.setStatus(reminder.getStatus());
            simpleView.setType(REMINDER_SEND);
            reminderSimpleViews.add(simpleView);
        }
        for(EventReminder eventReminder:eventReminders){

            ReminderSimpleView simpleView=new ReminderSimpleView();
            simpleView.setUserId(eventReminder.getUserId());
            simpleView.setRemindId(eventReminder.getRemindId());

            //获取原始reminder
            Reminder reminder=reminderDAO.getReminderById(eventReminder.getRemindId());
            simpleView.setContent(reminder.getContent());
            simpleView.setContentType(reminder.getContentType());

            simpleView.setRemindTime(eventReminder.getRemindTime());
            simpleView.setRemindType(eventReminder.getRemindType());
            simpleView.setStatus(eventReminder.getStatus());
            simpleView.setType(REMINDER_RECEIVE);
            reminderSimpleViews.add(simpleView);
        }

        return reminderSimpleViews;
    }
}
