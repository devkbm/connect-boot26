package com.like.cooperation.workschedule.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.service.ScheduleQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class ScheduleQueryController {

	private ScheduleQueryService service;
	
	public ScheduleQueryController(ScheduleQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/schedule")
	public ResponseEntity<?> getScheduleList(@ModelAttribute ScheduleDTO.SearchSchedule searchCondition) {										
		
		List<ScheduleDTO.ResponseSchedule> list = service.getScheduleList(searchCondition)
														 .stream()
														 .map( r -> ScheduleDTO.ResponseSchedule.convertResDTO(r))
														 .toList();
		
		return ResponseEntityUtil.toList(list							
										,MessageUtil.getQueryMessage(list.size()));												
	}
}
