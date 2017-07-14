package com.townscript.forum.model;

public class UserJdbc {
	private Integer userId;
	private String userName;
	private String password;
	private String userEmail;
	
	public UserJdbc() {
		
	}

	public UserJdbc(Integer userId, String userName, String password, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userEmail = userEmail;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
}
