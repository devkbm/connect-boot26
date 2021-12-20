package com.like.cooperation.workschedule.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.service.WorkGroupService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class WorkGroupController {	
		
	private WorkGroupService workGroupService;				
		
	public WorkGroupController(WorkGroupService workGroupService) {
		this.workGroupService = workGroupService;
	}			
	
	@GetMapping("/api/grw/workgroup/{id}")
	public ResponseEntity<?> getWorkGroup(@PathVariable(value="id") Long id) {
						
		WorkGroup entity = workGroupService.getWorkGroup(id);										
		
		WorkDTO.FormWorkGroup dto = WorkDTO.FormWorkGroup.convertDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto
							,dto == null ? 0 : 1							
							,"조회 되었습니다."
							,HttpStatus.OK);													
	}
		
	@PostMapping("/api/grw/workgroup")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.FormWorkGroup dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
				
		workGroupService.saveWorkGroup(dto);		
		
		return WebControllerUtil
				.getResponse(dto
							,dto != null ? 1 : 0							
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0)
							,HttpStatus.OK);
	}
			
}
