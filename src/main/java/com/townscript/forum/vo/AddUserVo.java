package com.townscript.forum.vo;

public class AddUserVo {
	
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userMobile;
	private byte[] userPropic;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public byte[] getUserPropic() {
		return userPropic;
	}
	public void setUserPropic(byte[] userPropic) {
		this.userPropic = userPropic;
	}
	
}
