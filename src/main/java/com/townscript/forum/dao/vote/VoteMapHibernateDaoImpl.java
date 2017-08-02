package com.townscript.forum.dao.vote;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.vote.VoteMapHibernate;

public class VoteMapHibernateDaoImpl extends HibernateDaoSupport implements VoteMapHibernateDao{
	
	@Override
	public boolean addVoteMap(VoteMapHibernate voteMap) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			session.save(voteMap);
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
			session.update(voteMap);
			return true;		
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Collection<VoteMapHibernate> getVoteByUserIdAndTopicId(long userId, long topicId){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM VoteMapHibernate WHERE userId=:userId AND topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("userId", userId);
			query.setParameter("topicId", topicId);
			Collection<VoteMapHibernate> voteMapList = query.list(); 
			
			return voteMapList;		
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public long getVoteCountByTopicId(int voteValue, long topicId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			String queryString = "select count(*) FROM VoteMapHibernate vm WHERE vm.topicId=:topicId AND vm.voteValue=:voteValue";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", topicId);
			query.setParameter("voteValue", voteValue);
			return (long)query.uniqueResult();
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}
}
