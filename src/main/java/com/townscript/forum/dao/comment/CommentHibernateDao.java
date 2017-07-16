package com.townscript.forum.dao.comment;

import com.townscript.forum.model.comment.CommentHibernate;

public interface CommentHibernateDao {
	CommentHibernate getCommentById(long id);
    long addComment(CommentHibernate comment);
}