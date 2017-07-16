package com.townscript.forum.dao.vote;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.vote.VoteMasterHibernate;

public class VoteMasterHibernateDaoImpl extends HibernateDaoSupport implements VoteMasterHibernateDao{
	
	@Override
	public int getVoteValueByVoteName(String voteName) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM "+VoteMasterHibernate.class.getName()+" WHERE voteName=:voteName"; 
			Query query = session.createQuery(queryString);
			query.setParameter("voteName", voteName);
			List<VoteMasterHibernate> voteList = query.list();
			return voteList.get(0).getVoteValue();
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
}
