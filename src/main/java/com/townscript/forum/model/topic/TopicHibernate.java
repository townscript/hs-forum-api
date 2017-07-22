package com.townscript.forum.model.topic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topic_data_table")
public class TopicHibernate {
	
	@Id @GeneratedValue
	@Column(name="TOPIC_ID")
	private long topicId;
	
	@Column(name="TOPIC_TITLE")
	private String topicTitle;
	
	@Column(name="TOPIC_TAGS")
	private String topicTags;
	
	@Column(name="DATE_TIME_CREATED")
	private Date topicDateTime;
	
	@Column(name="TOPIC_URL")
	private String topicUrl;
	
	@Column(name="TOPIC_DESCRIPTION")
	private String topicDescription;
	
	public TopicHibernate() {
	}

	public TopicHibernate(long topicId, String topicTitle, String topicTags, Date topicDateTime, String topicUrl,
			String topicDescription) {
		super();
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicTags = topicTags;
		this.topicDateTime = topicDateTime;
		this.topicUrl = topicUrl;
		this.topicDescription = topicDescription;
	}

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
