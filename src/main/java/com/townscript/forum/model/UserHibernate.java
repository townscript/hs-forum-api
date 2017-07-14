package com.townscript.forum.model;

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
	
	public UserHibernate() {
	}

	public UserHibernate(Integer userId, String userName, String password, String userEmail) {
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
