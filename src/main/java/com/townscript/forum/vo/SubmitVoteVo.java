package com.townscript.forum.vo;

public class SubmitVoteVo {
	
	private long topicId;
	private String userName;
	private int voteValue;
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
	public int getVoteValue() {
		return voteValue;
	}
	public void setVoteValue(int voteValue) {
		this.voteValue = voteValue;
	}
			
}
