package com.like.cooperation.team.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.domain.TeamRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.SystemUserService;

@Service
@Transactional
public class TeamService {
	
	private TeamRepository teamRepository;	
	private SystemUserService userService;	
	
	public TeamService(TeamRepository teamRepository					  
					  ,SystemUserService userService) {
		this.teamRepository = teamRepository;		
		this.userService = userService;		
	}
	
	public Team getTeam(Long teamId) {
		return teamRepository.findById(teamId).orElse(null);
	}
		
	public void saveTeam(TeamDTO.Form dto) {		
		Team entity = dto.teamId() == null ? null : teamRepository.findById(dto.teamId()).orElse(null);
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {
			dto.modify(entity);
		}
		
		teamRepository.save(entity);
	}
	
	public void deleteTeam(Long teamId) {
		teamRepository.deleteById(teamId);
	}
	
	/**
	 * 팀에 가입한다.
	 * @param teamId 팀 엔티티 Id
	 * @param userId 유저 엔티티 Id
	 * @return 
	 */
	public void joinTeam(Long teamId, String userId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		SystemUser member = userService.getUser(userId);			
		
		team.addMember(member);			
	}
	
	public void joinTeam(Long teamId, SystemUser user) {
		Team entity = teamRepository.findById(teamId).orElse(null);
		
		entity.addMember(user);
		
		teamRepository.save(entity);
	}
	
	public void joinTeam(Long teamId, List<SystemUser> userList) {
		Team entity = teamRepository.findById(teamId).orElse(null);
				
		for (SystemUser user : userList){
			entity.addMember(user);
		}
		
		teamRepository.save(entity);
	}
		
	/**
	 * 팀을 저장한다.
	 * @param team 팀 엔티티
	 * @param teamMemberList 팀원 엔티티
	 */
	public void saveTeam(Team team, List<SystemUser> userList) {		
									
		if (userList != null) {		
			List<TeamMember> teamMemberList = new ArrayList<TeamMember>();		
			for (SystemUser user: userList) {
				teamMemberList.add(new TeamMember(team, user));
			}	
			
			team.addMemberList(teamMemberList);			
		}
		
		teamRepository.save(team);
	}
		
	
		
	public List<SystemUser> getTeamMemberList(Long teamId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		
		return team.getMembers().stream().map(e -> e.getUser()).toList();
	}
									
	
}
