package com.like.cooperation.workschedule.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.service.ScheduleQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class ScheduleQueryController {

	private ScheduleQueryService service;
	
	public ScheduleQueryController(ScheduleQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/schedule")
	public ResponseEntity<?> getScheduleList(@ModelAttribute ScheduleDTO.Search searchCondition) {										
		
		List<ScheduleDTO.Response> list = service.getScheduleList(searchCondition)
												 .stream()
												 .map( r -> ScheduleDTO.Response.convert(r))
												 .toList();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
}
