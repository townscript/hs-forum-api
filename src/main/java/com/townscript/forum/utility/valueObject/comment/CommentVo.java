package com.townscript.forum.utility.valueObject.comment;

import java.util.Date;

public class CommentVo {
	private long commentId;
	private char commentType;
	private String commentValue;
	private Date commentDateTime;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public char getCommentType() {
		return commentType;
	}
	public void setCommentType(char commentType) {
		this.commentType = commentType;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}
	public Date getCommentDateTime() {
		return commentDateTime;
	}
	public void setCommentDateTime(Date commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
}
