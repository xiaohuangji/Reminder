package com.reminder.util;


import com.reminder.constant.ReminderConstant;

/**
 * Created by wills on 6/9/14.
 */
public class UrlEncryptUtil {

    /**
     * 加密
     * @param url
     * @return
     */
    public static String encrypt(String url){
        String azdg=  AzDGCrypt.encrypt(url, ReminderConstant.URLSECRETKEY);
        //将base64中的/转义成-
        return azdg.replace("/","-");
    }

    /**
     * 解密
     * @param secretUrl
     * @return
     */
    public static String decrypt(String secretUrl){
        //将-转化为/
        String  base64=secretUrl.replace("-","/");
        return AzDGCrypt.decrypt(base64, ReminderConstant.URLSECRETKEY);
    }

}
