package com.townscript.forum.dao.vote;

import java.util.Collection;

import com.townscript.forum.model.vote.VoteMapHibernate;

public interface VoteMapHibernateDao {
	boolean addVoteMap(VoteMapHibernate voteMap);
	boolean updateVoteMap(VoteMapHibernate voteMap);
	Collection<VoteMapHibernate> getVoteByUserIdAndTopicId(long userId, long topicId);
	long getVoteCountByTopicId(int voteValue, long topicId);
}