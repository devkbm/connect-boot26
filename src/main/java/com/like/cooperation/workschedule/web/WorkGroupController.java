package com.like.cooperation.workschedule.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.service.WorkGroupService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class WorkGroupController {	
		
	private WorkGroupService workGroupService;				
		
	public WorkGroupController(WorkGroupService workGroupService) {
		this.workGroupService = workGroupService;
	}			
	
	@GetMapping("/api/grw/workgroup/{id}")
	public ResponseEntity<?> getWorkGroup(@PathVariable Long id) {
						
		WorkGroup entity = workGroupService.getWorkGroup(id);										
		
		WorkDTO.FormWorkGroup dto = WorkDTO.FormWorkGroup.convertDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto										
							,"조회 되었습니다.");													
	}
		
	@PostMapping("/api/grw/workgroup")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.FormWorkGroup dto) {				
					
		workGroupService.saveWorkGroup(dto);		
		
		return WebControllerUtil
				.getResponse(dto											
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0));
	}
			
}
