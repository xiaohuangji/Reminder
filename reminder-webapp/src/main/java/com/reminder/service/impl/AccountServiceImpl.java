package com.reminder.service.impl;

import com.reminder.api.AccountService;
import com.reminder.api.PassportService;
import com.reminder.api.UserService;
import com.reminder.constant.RedisNSConstant;
import com.reminder.model.UserInfo;
import com.reminder.model.view.UserInfoLoginView;
import com.reminder.redis.client.RedisClient;
import com.reminder.service.dao.UserInfoDAO;
import com.reminder.service.dao.UserPwdDAO;
import com.reminder.util.MD5Util;
import com.reminder.util.SMSUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

import static com.reminder.constant.ResultConstant.*;

/**
 * Created by wills on 7/15/14.
 */
@Component("accountService")
public class AccountServiceImpl implements AccountService {
    
    private static final Logger LOGGER= Logger.getLogger(AccountServiceImpl.class);

    private RedisClient validationRedisClient=new RedisClient(RedisNSConstant.VALIDATION);

    private static int E_VERIFYCODE=2400;

    private static String validationCodeStr="注册验证码：";

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserPwdDAO userPwdDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PassportService passportService;

    @Override
    public UserInfoLoginView login(String cellphone, String password) {

        UserInfo userInfo=userService.getUserInfoByMobile(cellphone);
        if(userInfo==null){
            LOGGER.warn("login error - account is not exist:"+cellphone);
            return new  UserInfoLoginView(ACCOUNT_NOT_EXISTED);
        }

        int userId=userInfo.getId();
        if(!userPwdDAO.getPwd(userId).equals(md5Pwd(password,userId))){
            LOGGER.warn("login error - pwd error:"+cellphone);
            return new UserInfoLoginView(ACCOUNT_PWD_ERROR);
        }

        UserInfoLoginView userInfoLoginView=new UserInfoLoginView(OP_SUCC);

        userInfoLoginView.setUserInfo(userService.getUserInfo(userInfo.getId()));
        //替换passport
        passportService.removeTicket(userInfo.getId());
        userInfoLoginView.setUserPassport(passportService.createPassport(userInfo.getId()));

        LOGGER.info("login succ :"+cellphone);
        return userInfoLoginView;
    }

    @Override
    public UserInfoLoginView register(String name, String cellphone, String password, String validationCode) {

        //检查是否已注册过
        if(!isMobileAvailable(cellphone)){
            return new UserInfoLoginView(ACCOUNT_MOBILE_HAS_USED);
        }
        //检查验证码
        //81828384--backdoor。如果验证码为此，成功进入下一步。
        String realValidationCode=validationRedisClient.get(cellphone, String.class);
//        if(realValidationCode==null||realValidationCode.isEmpty()){
//            return new UserInfoLoginView(ACCOUNT_VALIDATION_EXPIRED);
//        }
        if(!validationCode.equals("81828384")&&!validationCode.equals(realValidationCode)){
            LOGGER.debug("validation code doesn't match:"+cellphone);
            return new UserInfoLoginView(ACCOUNT_VALIDATION_ERROR);
        }
        //清除验证码
        validationRedisClient.del(cellphone);

        //插入数据库
        UserInfo userInfo=new UserInfo();
        userInfo.setCellphone(cellphone);
        userInfo.setName(name);
        userInfo.setCreateTime(new Date());

        userInfoDAO.insertUserInfo(userInfo);
        int userId=userInfo.getId();
        userPwdDAO.changePwd(userId,md5Pwd(password, userId));

        UserInfoLoginView userInfoLoginView=new UserInfoLoginView(OP_SUCC);
        userInfoLoginView.setUserInfo(userInfo);
        //生成passport
        userInfoLoginView.setUserPassport(passportService.createPassport(userId));

        LOGGER.info("register succ, userId:"+userId+" cellphone:"+cellphone);
        return userInfoLoginView;
    }

    @Override
    public int sendValidationCode(String cellphone) {

        //判断如果已经是注册用户，返回错误
        if(!isMobileAvailable(cellphone)){
            LOGGER.warn("this mobile has registered :"+cellphone);
            return ACCOUNT_MOBILE_HAS_USED;
        }

        Random r = new Random();
        int x = r.nextInt(9999);
        validationRedisClient.setex(cellphone, x, E_VERIFYCODE);

        if(SMSUtil.sendSM(cellphone,validationCodeStr+x)){
            LOGGER.debug("gen verify code for mobile:" + cellphone + ":" + x);
            return OP_SUCC;
        }else{
            LOGGER.warn("send verify code error:"+cellphone);
            return OP_FAIL;
        }
    }

    @Override
    public int changePassword(int _userId, String oldPassword, String newPassword) {
        String oldRealPwd=userPwdDAO.getPwd(_userId);
        if(oldRealPwd.equals(md5Pwd(oldPassword,_userId))){
            //旧密码验证通过
            LOGGER.debug("oldpwd succ,start to update newpwd:"+_userId);
            int result=userPwdDAO.changePwd(_userId,md5Pwd(newPassword,_userId));
            return (result!=0?OP_SUCC:OP_FAIL);
        }else{
            LOGGER.warn("oldpwd error,changepwd fail:"+_userId);
            return ACCOUNT_OLD_PWD_ERROR;
        }
    }


    @Override
    public int resetPassword(String cellphone, String validationCode, String password) {
        UserInfo userInfo=userService.getUserInfoByMobile(cellphone);
        if(userInfo==null){
            return ACCOUNT_NOT_EXISTED;
        }

        String realValidationCode=validationRedisClient.get(cellphone, String.class);
        if(!validationCode.equals(realValidationCode)){
            LOGGER.info("validation code doesn't match:" + cellphone);
            return ACCOUNT_VALIDATION_ERROR;
        }
        //清除验证码
        validationRedisClient.del(cellphone);
        userPwdDAO.changePwd(userInfo.getId(),password);
        LOGGER.info("reset password succ,"+cellphone);
        return OP_SUCC;
    }

    /**
     * 判断手机是否可用,即手机号未被注册说明可用
     * @param cellphone
     * @return
     */
    private boolean isMobileAvailable(String cellphone){
        return userService.getUserInfoByMobile(cellphone)==null?true:false;
    }

    private String md5Pwd(String password,int userId){
        return MD5Util.MD5Encrypt(password+userId);
    }
}
