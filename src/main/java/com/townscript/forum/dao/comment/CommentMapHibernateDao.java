package com.townscript.forum.dao.comment;

import java.util.Collection;

import com.townscript.forum.model.comment.CommentMapHibernate;

public interface CommentMapHibernateDao {
	boolean addCommentMap(CommentMapHibernate commentMap);
	Collection<CommentMapHibernate> getCommentMapByTopicId(long topicId);
	CommentMapHibernate getCommentMapByCommentId(long commentId);
	Collection<CommentMapHibernate> getAllCommentMaps();
}