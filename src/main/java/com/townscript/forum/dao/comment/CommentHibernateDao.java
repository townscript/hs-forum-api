package com.townscript.forum.dao.comment;

import java.util.Collection;

import com.townscript.forum.model.comment.CommentHibernate;

public interface CommentHibernateDao {
	CommentHibernate getCommentById(long id);
    long addComment(CommentHibernate comment);
    Collection<CommentHibernate> getAllComments();
}