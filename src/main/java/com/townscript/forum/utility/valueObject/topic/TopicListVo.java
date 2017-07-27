package com.townscript.forum.utility.valueObject.topic;

import java.util.List;

public class TopicListVo {
	private String status;
	private List<TopicVo> topicList;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TopicVo> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<TopicVo> topicList) {
		this.topicList = topicList;
	}
	
}
