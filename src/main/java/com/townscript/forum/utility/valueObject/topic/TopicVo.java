package com.townscript.forum.utility.valueObject.topic;

import java.util.Date;

public class TopicVo {
	private long topicId;
	private String topicTitle;
	private String topicTags;
	private Date topicDateTime;
	private String topicUrl;
	private String topicDescription;
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getTopicTags() {
		return topicTags;
	}
	public void setTopicTags(String topicTags) {
		this.topicTags = topicTags;
	}
	public Date getTopicDateTime() {
		return topicDateTime;
	}
	public void setTopicDateTime(Date topicDateTime) {
		this.topicDateTime = topicDateTime;
	}
	public String getTopicUrl() {
		return topicUrl;
	}
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}
	public String getTopicDescription() {
		return topicDescription;
	}
	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}
	
}
