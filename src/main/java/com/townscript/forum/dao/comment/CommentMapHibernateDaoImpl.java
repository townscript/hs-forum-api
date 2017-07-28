package com.townscript.forum.dao.comment;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.comment.CommentMapHibernate;

public class CommentMapHibernateDaoImpl extends HibernateDaoSupport implements CommentMapHibernateDao{

	@Override
	public boolean addCommentMap(CommentMapHibernate commentMap) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			session.save(commentMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Collection<CommentMapHibernate> getCommentMapByTopicId(long topicId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			String queryString = "FROM CommentMapHibernate WHERE topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", topicId);
			Collection<CommentMapHibernate> commentMapColl = query.list();
			return commentMapColl;
		}	
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CommentMapHibernate getCommentMapByCommentId(long commentId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			String queryString = "FROM CommentMapHibernate WHERE commentId=:commentId";
			Query query = session.createQuery(queryString);
			query.setParameter("commentId", commentId);
			List<CommentMapHibernate> commentMapList = query.list();
			return commentMapList.get(0);
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Collection<CommentMapHibernate> getAllCommentMaps()
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			List<CommentMapHibernate> commentMapList = session.createQuery("FROM CommentMapHibernate").list();
			return commentMapList;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
