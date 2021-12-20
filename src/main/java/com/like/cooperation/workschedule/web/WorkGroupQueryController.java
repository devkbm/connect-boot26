package com.like.cooperation.workschedule.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.service.WorkGroupQueryService;
import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class WorkGroupQueryController {

	private WorkGroupQueryService service;
	
	public WorkGroupQueryController(WorkGroupQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/workgroup")
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkDTO.SearchWorkGroup searchCondition) {
						
		List<WorkGroup> workGroupList = service.getWorkGroupList(searchCondition);				
		
		return WebControllerUtil
				.getResponse(workGroupList
							,workGroupList.size()							
							,workGroupList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
	
	@GetMapping("/api/grw/myworkgroup")
	public ResponseEntity<?> getWorkGroupList() {
						
		String sessionId = SessionUtil.getUserId(); // SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<WorkGroup> workGroupList = service.getMyWorkGroupList(sessionId);				
		
		return WebControllerUtil
				.getResponse(workGroupList
							,workGroupList.size()							
							,workGroupList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
}
