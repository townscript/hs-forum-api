package com.townscript.forum.vo;

public class AddCommentVo {
	
	private long topicId;
	private String userName;
	private String commentValue;
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}
		
}
