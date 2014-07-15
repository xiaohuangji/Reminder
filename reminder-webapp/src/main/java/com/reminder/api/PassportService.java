package com.reminder.api;

import com.reminder.model.UserPassport;

/**
 * passport 服务，就是t票服务
 * @author xinquan.guan
 *
 */

public interface PassportService {
	/**
	 * 通过ticket，换取UserPassport
	 * @param ticket
	 * @return
	 */
	 public UserPassport getPassportByTicket(String ticket);
	 
	 /**
	  * 通过userPassport中的userId，创建passport
	  * @param userPassport
	  * @return
	  */
//	 public String createTicket(UserPassport userPassport);

	 /**
	  * 创建一个passport
	  * @return
	  */
	 public UserPassport createPassport(int userId);
	 
	 /**
	  * 
	  * @param userId
	  */
	 public void removeTicket(int userId);
	  
}
