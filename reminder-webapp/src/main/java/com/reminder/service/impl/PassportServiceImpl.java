package com.reminder.service.impl;

import com.reminder.api.PassportService;
import com.reminder.model.UserPassport;
import com.reminder.mcp.passport.TicketUtils;
import com.reminder.redis.client.RedisClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("passportService")
public class PassportServiceImpl implements PassportService {

    public static final String PASSPORT_PREFIX = "passport";

    public static final String SIG_PREFIX = "sig";

    private RedisClient passPortRedisClient=new RedisClient(PASSPORT_PREFIX);

    private RedisClient sigRedisClient=new RedisClient(SIG_PREFIX);

    public static final int EXPIRE = 86400*365;/**一年 **/
    
    public static final Logger logger = Logger.getLogger(PassportService.class);

	public UserPassport getPassportByTicket(String ticket) {
		UserPassport userPassport = null;
		if (StringUtils.isEmpty(ticket)) {
			return userPassport;
		}
		int uid = TicketUtils.decryptTicket(ticket);
		if (uid <= 0) {
			return userPassport;
		}		
		userPassport = passPortRedisClient.get(String.valueOf(uid),UserPassport.class);
		return userPassport;
	}

	public String createTicket(UserPassport userPassport) {
		String ticket = null;
		if (userPassport == null || userPassport.getUserId() == 0) {
			return ticket;
		}
		ticket = TicketUtils.generateTicket(userPassport.getUserId());
		userPassport.setTicket(ticket);
		if (!StringUtils.isEmpty(ticket)) {
            passPortRedisClient.setex(String.valueOf(userPassport.getUserId()), userPassport,EXPIRE);
		}
		return ticket;
	}

	@Override
	public void removeTicket(int userId) {
        passPortRedisClient.del(String.valueOf(userId));
	}

	@Override
	public Integer getSig(String sig) {
        return sigRedisClient.get(sig,Integer.class);
	}

	@Override
	public void addSig(String sig, Integer value) {
        sigRedisClient.setex(sig, value, 5);

    }

	@Override
	public UserPassport createPassport(UserPassport userPassport) {
		String ticket = null;
		
		if (userPassport == null || userPassport.getUserId() == 0) {
			return null;
		}
		ticket = TicketUtils.generateTicket(userPassport.getUserId());
		userPassport.setTicket(ticket);
		userPassport.setUserSecretKey(generateSecretKey());
		if (!StringUtils.isEmpty(ticket)) {
            passPortRedisClient.setex(String.valueOf(userPassport.getUserId()), userPassport,EXPIRE);
		}
		return userPassport;
	}
	
	   /**
     * 生成密钥
     * 
     * @return
     */
    public static String generateSecretKey() {
        UUID uuid = UUID.randomUUID();
        long now = System.currentTimeMillis();
        return DigestUtils.md5Hex(uuid.toString() + now);
    }

}