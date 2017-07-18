package com.townscript.forum.model.comment;

import javax.persistence.*;

@Entity
@Table(name = "comment_data_table")
public class CommentHibernate {
	
	@Id @GeneratedValue
	@Column(name="COMMENT_ID")
	private Long commentId;
	
	@Column(name="COMMENT_TYPE")
	private String commentType;
	
	@Column(name="COMMENT_VALUE")
	private String commentValue;
	
	@Column(name="DATE_TIME_CREATED")
	private String commentDateTime;
	
	public CommentHibernate() {
	}
	
	public CommentHibernate(Long commentId, String commentType, String commentValue, String commentDateTime) {
		super();
		this.commentId = commentId;
		this.commentType = commentType;
		this.commentValue = commentValue;
		this.commentDateTime = commentDateTime;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}

	public String getCommentDateTime() {
		return commentDateTime;
	}

	public void setCommentDateTime(String commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
}
