package com.townscript.forum.model.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_data_table")
public class CommentHibernate {
	
	@Id @GeneratedValue
	@Column(name="COMMENT_ID")
	private long commentId;
	
	@Column(name="COMMENT_TYPE")
	private char commentType;
	
	@Column(name="COMMENT_VALUE")
	private String commentValue;
	
	@Column(name="DATE_TIME_CREATED")
	private Date commentDateTime;
	
	public CommentHibernate() {
	}
	
	public CommentHibernate(long commentId, char commentType, String commentValue, Date commentDateTime) {
		super();
		this.commentId = commentId;
		this.commentType = commentType;
		this.commentValue = commentValue;
		this.commentDateTime = commentDateTime;
	}

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
