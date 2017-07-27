package com.townscript.forum.utility.valueObject.topic;

import java.util.Date;
import java.util.List;

import com.townscript.forum.utility.valueObject.comment.CommentVo;

public class TopicVo {
	private Long id;
	private String title;
	private String tags;
	private Date createdAt;
	private String createdBy;
	private String url;
	private String description;
	private List<CommentVo> commentList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CommentVo> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentVo> commentList) {
		this.commentList = commentList;
	}
	
}
