package com.like.cooperation.team.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class TeamMemberId implements Serializable {		
	
	private static final long serialVersionUID = 438709457020029955L;

	@Column(name="TEAM_ID")
	Long teamId;
		
	@Column(name="USER_ID")
	String userId;	
	
	public TeamMemberId(Long teamId, String userId) {
		this.teamId = teamId;
		this.userId = userId;
	}
	
}
