package com.like.cooperation.team.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.service.TeamQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.SystemUser;

@RestController
public class TeamQueryController {

	private TeamQueryService service;
	
	public TeamQueryController(TeamQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/team")
	public ResponseEntity<?> getTeamList(@ModelAttribute TeamDTO.Search searchCondition) {
						
		List<Team> list = service.getTeamList(searchCondition);				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/team/{id}/member")
	public ResponseEntity<?> getTeamMemberList(@PathVariable Long id) {
						
		List<TeamMember> list = service.getTeamMemberList(id);				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/allmember")
	public ResponseEntity<?> getAllMemberList(SystemUserDTO.Search condition) {
				
		List<SystemUser> list = service.getAllMember(condition);						 				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
