package com.townscript.forum.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_data_table")
public class UserHibernate {
	
	@Id @GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="USER_EMAIL")
	private String userEmail;
	
	@Column(name="USER_MOBILE")
	private String userMobile;

	@Column(name="DATE_TIME_CREATED")
	private String userDateTime;

	@Column(name="USER_PROPIC")
	private byte[] userPropic;
	
	public UserHibernate() {
	}

	public UserHibernate(Integer userId, String userName, String password, String userEmail, String userMobile,
			String userDateTime, byte[] userPropic) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.userDateTime = userDateTime;
		this.userPropic = userPropic;
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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserDateTime() {
		return userDateTime;
	}

	public void setUserDateTime(String userDateTime) {
		this.userDateTime = userDateTime;
	}

	public byte[] getUserPropic() {
		return userPropic;
	}

	public void setUserPropic(byte[] userPropic) {
		this.userPropic = userPropic;
	}
}
