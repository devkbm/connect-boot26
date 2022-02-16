package com.like.cooperation.team.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.domain.AuditEntity;
import com.like.system.core.web.exception.BusinessException;
import com.like.system.core.web.exception.ErrorCode;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "GRWTEAM")
public class Team extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 7255535665611002987L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEAM_ID")
	Long teamId;
	
	@Comment("팀명")
	@Column(name="TEAM_NAME")
	String teamName;
	
	/*@OneToOne
	@JoinColumn(name="USER_ID")
	private User manager;*/ 
	
	@JsonIgnore
	@OneToMany(mappedBy="team", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	List<TeamMember> members = new ArrayList<TeamMember>();			
	
	public Team(String teamName) {
		this.teamName = teamName;		
	}	
	
	public void changeTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public List<TeamMember> getTeamMemberList() {
		return this.members;
	}	
		
	public List<SystemUser> getMemberList() {
		/*
		// Java 7
		List<Member> memberList = new ArrayList<>();
		for (JoinTeam joinTeam : this.memberList) {
			memberList.add(joinTeam.getMember());
		}
		
		return memberList;
		*/
		return this.members
				.stream()
				.map(r -> r.getUser())
				.collect(Collectors.toList());
	}
	
	public void addMember(SystemUser user)
	{
		boolean isExist = this.members.stream()
									  .map(r -> r.getUser())					
									  .anyMatch(e -> e.equals(user));
		
		if (isExist)
			throw new BusinessException("동일한 데이터가 존재합니다. 아이디 : " + user.getUserId(), ErrorCode.ID_DUPLICATION);
		
		this.members.add(new TeamMember(this, user));
	}
	
	public void addMemberList(List<TeamMember> memberList) {
		this.members = memberList;
	}
	
}
