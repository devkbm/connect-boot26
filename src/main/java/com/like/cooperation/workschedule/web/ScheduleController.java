package com.like.cooperation.workschedule.web;

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

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.service.ScheduleService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class ScheduleController {

	private ScheduleService service;
	
	public ScheduleController(ScheduleService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/schedule/{id}")	
	public ResponseEntity<?> getSchedule(@PathVariable(value="id") Long id) {
						
		Schedule entity = service.getSchedule(id);							
		
		ScheduleDTO.ResponseSchedule dto = ScheduleDTO.ResponseSchedule.convertResDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto
							,dto == null ? 0 : 1							
							,"조회 되었습니다."
							,HttpStatus.OK);													
	}
		
	@PostMapping("/api/grw/schedule")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody ScheduleDTO.FormSchedule dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
					
		service.saveSchedule(dto);		
										 					
		return WebControllerUtil
				.getResponse(dto
							,dto != null ? 1 : 0							
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/grw/schedule/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		service.deleteSchedule(id);							
				
		return WebControllerUtil
				.getResponse(null
							,1							
							,"삭제 되었습니다."
							,HttpStatus.OK);													
	}
}
