package com.townscript.forum.service.topic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.constants.Constants;
import com.townscript.forum.dao.topic.TopicHibernateDao;
import com.townscript.forum.dao.topic.TopicMapHibernateDao;
import com.townscript.forum.dao.vote.VoteMapHibernateDao;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.topic.TopicMapHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.user.UserHibernateService;

@Service
@Transactional
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicHibernateDao topicDao;
	@Autowired
	private TopicMapHibernateDao topicMapDao;
	@Autowired
	private UserHibernateService userService;
	@Autowired
	private VoteMapHibernateDao voteMapDao;
	
	public TopicHibernateDao gettopicDao() {
		return topicDao;
	}

	public void settopicDao(TopicHibernateDao topicDao) {
		this.topicDao = topicDao;
	}
	
	public TopicMapHibernateDao getTopicMapDao() {
		return topicMapDao;
	}

	public void setTopicMapDao(TopicMapHibernateDao topicMapDao) {
		this.topicMapDao = topicMapDao;
	}
	
	public UserHibernateService getUserService() {
		return userService;
	}

	public void setUserService(UserHibernateService userService) {
		this.userService = userService;
	}
	
	public VoteMapHibernateDao getVoteMapDao() {
		return voteMapDao;
	}

	public void setVoteMapDao(VoteMapHibernateDao voteMapDao) {
		this.voteMapDao = voteMapDao;
	}

	@Override
	public long createTopic(TopicHibernate topic, UserHibernate user) {
		// TODO Auto-generated method stub
		
		long topicId = topicDao.createTopic(topic);
		TopicMapHibernate topicMap = new TopicMapHibernate();
		topicMap.setTopicId(topicId);
		topicMap.setUserId(user.getUserId());
        if(topicMapDao.addTopicMap(topicMap))
        {
        	return topicId;
        }
        return Constants.DEF_ID;
	}

	@Override
	public String updateTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		return topicDao.updateTopic(topic);
	}

	@Override
	public String deleteTopicById(long topicId) {
		// TODO Auto-generated method stub
		if(topicMapDao.deleteTopicMap(topicId))
		{
			return topicDao.deleteTopicById(topicId);
		}
		return Constants.MSG_FAIL;
	}

	@Override
	public Collection<TopicHibernate> getAllTopics() {
		// TODO Auto-generated method stub
		return topicDao.getAllTopics();
	}

	@Override
	public Collection<TopicHibernate> getAllTopicsOnLoad() {
		// TODO Auto-generated method stub
		//need to modify it later: with resultset limit, etc
		return topicDao.getAllTopics();
	}

	@Override
	public TopicHibernate getTopicById(long topicId) {
		// TODO Auto-generated method stub
		return topicDao.getTopicById(topicId);
	}

	@Override
	public Collection<TopicHibernate> getTopicsByUsername(String userName) {
		// TODO Auto-generated method stub
		//return topicDao.getTopicsByUsername();
		//need to modify it: use userTopicMap table
		long userId = userService.getUserIdByUserName(userName);
		Collection<TopicMapHibernate> topicMapColl = topicMapDao.getTopicMapByUserId(userId);
		List<TopicMapHibernate> topicMapList = new ArrayList(topicMapColl);
		List<TopicHibernate> topicList = new ArrayList();
		for(int i=0;i<topicMapList.size();i++)
		{
			topicList.add(topicDao.getTopicById(topicMapList.get(i).getTopicId()));
		}
		return topicList;
	}
	
	@Override
	public long getVoteCountByTopicId(int voteValue, long topicId)
	{
		return voteMapDao.getVoteCountByTopicId(voteValue, topicId);
	}

}
