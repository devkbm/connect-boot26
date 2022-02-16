package com.like.cooperation.team.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.system.core.domain.AuditEntity;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTEAMUSER")
public class TeamMember extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -2005013743007739568L;

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
	private String authority;
	
	public TeamMember(TeamMemberId teamMemberId) {
		this.id = teamMemberId;
	}
	
	public TeamMember(Team team, SystemUser user) {
		this.id = new TeamMemberId(team.getTeamId(), user.getUserId());
	}
		
	public Team getTeam() {
		return this.team;
	}
		
	public SystemUser getUser() {
		return this.user;
	}
	
}
