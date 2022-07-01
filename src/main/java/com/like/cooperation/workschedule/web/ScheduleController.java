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
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

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
		
		return ResponseEntityUtil.toOne(dto													
									   ,MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
		
	@PostMapping("/api/grw/schedule")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody ScheduleDTO.FormSchedule dto) {				
		
		service.saveSchedule(dto);		
										 					
		return ResponseEntityUtil.toOne(dto		
									   ,MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/schedule/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		service.deleteSchedule(id);							
				
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getDeleteMessage(1));													
	}
}
