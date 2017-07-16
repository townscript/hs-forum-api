package com.townscript.forum.dao.comment;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.comment.CommentHibernate;

public class CommentHibernateDaoImpl extends HibernateDaoSupport implements CommentHibernateDao{
	
	@Override
	public CommentHibernate getCommentById(long id) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM "+CommentHibernate.class.getName()+" WHERE commentId=:commentId";
			Query query = session.createQuery(queryString);
			query.setParameter("commentId", id);
			List<CommentHibernate> commentList = query.list();
			return commentList.get(0);			
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public long addComment(CommentHibernate comment) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().saveOrUpdate(comment);
			return comment.getCommentId();
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}
}
