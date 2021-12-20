package com.like.cooperation.team.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.service.TeamService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.UserRepository;


@RestController
public class TeamController {
		
	private TeamService teamService;			
	private UserRepository userRepository;
	
	public TeamController(TeamService teamService
						 ,UserRepository userRepository) {
		this.teamService = teamService;
		this.userRepository = userRepository;
	}
		
		
	@GetMapping("/api/grw/team/{id}")
	public ResponseEntity<?> getTeam(@PathVariable("id") Long teamId) {
						
		Team team = teamService.getTeam(teamId);				
		
		TeamDTO.FormTeam dto = TeamDTO.FormTeam.convert(team);
		
		return WebControllerUtil.getResponse(dto,
				"조회 되었습니다.",
				HttpStatus.OK);													
	}
		
	@PostMapping("/api/grw/team")
	public ResponseEntity<?> saveTeam(@Valid @RequestBody TeamDTO.FormTeam dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
		
		Team team = dto.getTeamId() == null ? null : teamService.getTeam(dto.getTeamId());		
		
		if (team == null) {
			team = new Team(dto.getTeamName());						
		} else {
			team.changeTeamName(dto.getTeamName());
		}
						
		List<SystemUser> userList = null;		
		if (dto.getMemberList() != null) {
			userList = userRepository.findAllById(dto.getMemberList());					 											
		}
		
		teamService.saveTeam(team, userList);		
										 					
		return WebControllerUtil.getResponse(team,				
				String.format("%d 건 저장되었습니다.", team != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/api/grw/team/{id}/member")
	public ResponseEntity<?> getTeamMemberList(@PathVariable("id") Long teamId) {
						
		List<SystemUser> memberList = teamService.getTeamMemberList(teamId);												
		
		return WebControllerUtil.getResponse(memberList,			
				"조회 되었습니다.",
				HttpStatus.OK);
	}		
				
	
	@DeleteMapping("/api/grw/team/{teamId}")
	public ResponseEntity<?> delBoard(@PathVariable("teamId") Long teamId) {					
												
		teamService.deleteTeam(teamId);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@PostMapping("/api/grw/team/{teamId}/join/{userId}")
	public ResponseEntity<?> joinTeam(@PathVariable("teamId") Long teamId
									 ,@PathVariable("userId") String userId) {				

		teamService.joinTeam(teamId, userId);			
										 					
		return WebControllerUtil.getResponse(null,				
				String.format("팀에 등록 되었습니다."), 
				HttpStatus.OK);
	}
			
}
