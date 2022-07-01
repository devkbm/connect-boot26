package com.like.cooperation.workschedule.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.service.WorkGroupQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class WorkGroupQueryController {

	private WorkGroupQueryService service;
	
	public WorkGroupQueryController(WorkGroupQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/workgroup")
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkDTO.SearchWorkGroup searchCondition) {
						
		List<WorkGroup> list = service.getWorkGroupList(searchCondition);				
		
		return ResponseEntityUtil.toList(list	
										,MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/myworkgroup")
	public ResponseEntity<?> getWorkGroupList() {							
		
		List<WorkGroup> list = service.getMyWorkGroupList(SessionUtil.getUserId());				
		
		return ResponseEntityUtil.toList(list													
										,MessageUtil.getQueryMessage(list.size()));												
	}
}
