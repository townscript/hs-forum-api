package com.townscript.forum.utility.valueObject.user;

import java.util.Date;

public class UserVo {
	private long userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userMobile;
	private Date userDateTime;
	private byte[] userPropic;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
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
	public Date getUserDateTime() {
		return userDateTime;
	}
	public void setUserDateTime(Date userDateTime) {
		this.userDateTime = userDateTime;
	}
	public byte[] getUserPropic() {
		return userPropic;
	}
	public void setUserPropic(byte[] userPropic) {
		this.userPropic = userPropic;
	}
}
