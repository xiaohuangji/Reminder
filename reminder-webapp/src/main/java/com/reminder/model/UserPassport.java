package com.reminder.model;

import java.io.Serializable;

public class UserPassport  implements Serializable{
	  private static final long serialVersionUID = 1L;

	    private int userId;

	    private String ticket;

		// 创建时间
	    private long createTime;

	    // 用户的密钥
	    private String userSecretKey;
	    
	    public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getTicket() {
			return ticket;
		}

		public void setTicket(String ticket) {
			this.ticket = ticket;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public String getUserSecretKey() {
			return userSecretKey;
		}

		public void setUserSecretKey(String userSecretKey) {
			this.userSecretKey = userSecretKey;
		}
}
