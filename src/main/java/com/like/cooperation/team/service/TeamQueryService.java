package com.like.cooperation.team.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamRepository;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.UserQueryService;

@Service
@Transactional(readOnly=true)
public class TeamQueryService {

	private TeamRepository teamQueryRepository;
	private UserQueryService userQueryService;
	
	public TeamQueryService(TeamRepository teamQueryRepository
						   ,UserQueryService userQueryService) {
		this.teamQueryRepository = teamQueryRepository;
		this.userQueryService = userQueryService;
	}
	
	/**
	 * 조건에 해당하는 팀 명단을 조회한다.
	 * @param searchCondition 조회조건
	 * @return List<Team> 팀 명단
	 */
	public List<Team> getTeamList(TeamDTO.SearchCondition searchCondition) {
		Iterable<Team> result = teamQueryRepository.findAll(searchCondition.getCondition());
		List<Team> list = new ArrayList<>();
		
		result.forEach(e -> list.add(e));
		
		return list;
	}
	
	/**
	 * 조건에 해당하는 유저 정보를 조회한다.
	 * @param searchCondition 조회 조건
	 * @return User 
	 */
	public List<SystemUser> getAllMember(UserDTO.SearchUser searchCondition) {
		return userQueryService.getUserList(searchCondition);
	}
	
	
}
