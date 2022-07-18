package com.like.cooperation.team.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.team.boundary.TeamDTO;

@Repository
public interface TeamQueryRepository {

	List<Team> getTeamList(TeamDTO.Search searchCondition);	
	
	List<TeamMember> getTeamMemberList(Long teamId);
}
