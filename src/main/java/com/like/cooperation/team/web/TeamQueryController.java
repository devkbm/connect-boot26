package com.like.cooperation.team.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.service.TeamQueryService;
import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;

@RestController
public class TeamQueryController {

	private TeamQueryService service;
	
	public TeamQueryController(TeamQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/team")
	public ResponseEntity<?> getTeamList(@ModelAttribute TeamDTO.SearchCondition searchCondition) {
						
		List<Team> teamList = service.getTeamList(searchCondition);				
		
		return WebControllerUtil.getResponse(teamList
											,teamList.size() + "건 조회 되었습니다.");												
	}
	
	@GetMapping("/api/grw/team/{id}/member")
	public ResponseEntity<?> getTeamMemberList(@PathVariable Long id) {
						
		var list = service.getTeamMemberList(id);				
		
		return WebControllerUtil.getResponse(list
											,"{0} 건 조회되었습니다.".formatted(list.size()));												
	}
	
	@GetMapping("/api/grw/allmember")
	public ResponseEntity<?> getAllMemberList(UserDTO.SearchUser condition) {
				
		List<SystemUser> userList = service.getAllMember(condition);						 				
		
		return WebControllerUtil.getResponse(userList
											,"조회 되었습니다.");
	}
	
}
