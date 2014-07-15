package com.reminder.service.impl;

import com.reminder.api.ApiSigService;
import com.reminder.constant.RedisNSConstant;
import com.reminder.redis.client.RedisClient;
import org.springframework.stereotype.Component;

/**
 * Created by wills on 7/15/14.
 */
@Component("apiSigService")
public class ApiSigServiceImpl implements ApiSigService {

    private RedisClient sigRedisClient=new RedisClient(RedisNSConstant.SIG);

    @Override
    public Integer getSig(String sig) {
        return sigRedisClient.get(sig,Integer.class);
    }

    @Override
    public void addSig(String sig, Integer value) {
        sigRedisClient.setex(sig, value, 5);

    }
}
