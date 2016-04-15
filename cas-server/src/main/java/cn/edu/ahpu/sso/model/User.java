package cn.edu.ahpu.sso.model;

public class User {
		private long userId;
	  	public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		private String loginAcct;  
	    private String username;  
	      
	    public String getLoginAcct() {
			return loginAcct;
		}
		public void setLoginAcct(String loginAcct) {
			this.loginAcct = loginAcct;
		}
		public String getUsername() {  
	        return username;  
	    }  
	    public void setUsername(String username) {  
	        this.username = username;  
	    }  
}
