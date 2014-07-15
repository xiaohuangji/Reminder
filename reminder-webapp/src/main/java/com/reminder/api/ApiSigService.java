package com.reminder.api;

/**
 * Created by wills on 7/15/14.
 */
public interface ApiSigService {

    /**
     *
     * @param sig 获取sig对应的值
     * @return
     */
    public Integer getSig(String sig);

    /**
     * 设置sig的值
     * @param sig
     * @param value
     */
    public void addSig(String sig, Integer value);
}
