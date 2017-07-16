package com.townscript.forum.dao.vote;

import com.townscript.forum.model.vote.VoteMapHibernate;

public interface VoteMapHibernateDao {
	boolean addVoteMap(VoteMapHibernate voteMap);
	boolean updateVoteMap(VoteMapHibernate voteMap);
}