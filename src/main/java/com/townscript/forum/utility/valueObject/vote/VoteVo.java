package com.townscript.forum.utility.valueObject.vote;

public class VoteVo {
	private Long id;
	private int value;
	private Long topicId;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
