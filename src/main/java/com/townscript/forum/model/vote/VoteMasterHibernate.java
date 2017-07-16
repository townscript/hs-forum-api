package com.townscript.forum.model.vote;

import javax.persistence.*;

@Entity
@Table(name = "vote_master_table")
public class VoteMasterHibernate {
	
	@Id @GeneratedValue
	@Column(name="VOTE_NAME")
	private String voteName;

	@Id @GeneratedValue
	@Column(name="VOTE_VALUE")
	private Integer voteValue;
	
	public VoteMasterHibernate() {
	}

	public VoteMasterHibernate(String voteName, Integer voteValue) {
		super();
		this.voteName = voteName;
		this.voteValue = voteValue;
	}

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public Integer getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(Integer voteValue) {
		this.voteValue = voteValue;
	}
}
