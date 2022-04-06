package com.like.cooperation.team.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.system.core.domain.AuditEntity;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"team","user"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTEAMUSER")
public class TeamMember extends AuditEntity {	

	@EmbeddedId
	TeamMemberId id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
	private Team team;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="USER_ID", insertable = false, updatable = false)
	private SystemUser user;	
	
	//@Comment("권한")
	String authority;	
	
	public TeamMember(Team team, SystemUser user) {
		this.id = new TeamMemberId(team.getTeamId(), user.getUserId());
		this.team = team;
		this.user = user;
	}
		
	public Team getTeam() {
		return this.team;
	}
		
	public SystemUser getUser() {
		return this.user;
	}
	
}
