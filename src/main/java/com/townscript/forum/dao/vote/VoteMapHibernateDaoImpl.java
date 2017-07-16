package com.townscript.forum.dao.vote;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.vote.VoteMapHibernate;

public class VoteMapHibernateDaoImpl extends HibernateDaoSupport implements VoteMapHibernateDao{
	
	@Override
	public boolean addVoteMap(VoteMapHibernate voteMap) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().saveOrUpdate(voteMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateVoteMap(VoteMapHibernate voteMap) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().update(voteMap);
			return true;		
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
}
