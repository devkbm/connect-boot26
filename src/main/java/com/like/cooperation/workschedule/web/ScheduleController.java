package com.like.cooperation.workschedule.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.service.ScheduleService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class ScheduleController {

	private ScheduleService service;
	
	public ScheduleController(ScheduleService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/schedule/{id}")	
	public ResponseEntity<?> getSchedule(@PathVariable Long id) {
						
		Schedule entity = service.getSchedule(id);							
		
		ScheduleDTO.ResponseSchedule dto = ScheduleDTO.ResponseSchedule.convertResDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto													
							,"조회 되었습니다.");													
	}
		
	@PostMapping("/api/grw/schedule")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody ScheduleDTO.FormSchedule dto) {				
		
		service.saveSchedule(dto);		
										 					
		return WebControllerUtil
				.getResponse(dto								
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0));
	}
	
	@DeleteMapping("/api/grw/schedule/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		service.deleteSchedule(id);							
				
		return WebControllerUtil
				.getResponse(null											
							,"삭제 되었습니다.");													
	}
}
