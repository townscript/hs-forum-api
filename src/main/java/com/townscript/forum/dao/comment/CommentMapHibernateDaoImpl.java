package com.townscript.forum.dao.comment;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.comment.CommentMapHibernate;

public class CommentMapHibernateDaoImpl extends HibernateDaoSupport implements CommentMapHibernateDao{

	@Override
	public boolean addCommentMap(CommentMapHibernate commentMap) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().saveOrUpdate(commentMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
}
