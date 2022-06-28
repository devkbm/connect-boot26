package com.like.cooperation.workschedule.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.service.ScheduleQueryService;
import com.like.system.core.web.util.WebResponseUtil;

@RestController
public class ScheduleQueryController {

	private ScheduleQueryService service;
	
	public ScheduleQueryController(ScheduleQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/schedule")
	public ResponseEntity<?> getScheduleList(@ModelAttribute ScheduleDTO.SearchSchedule searchCondition) {
						
		List<Schedule> workGroupList = service.getScheduleList(searchCondition);				
		
		List<ScheduleDTO.ResponseSchedule> dtoList = workGroupList.stream()
																  .map( r -> ScheduleDTO.ResponseSchedule.convertResDTO(r))
																  .toList();
		
		return WebResponseUtil.toList(dtoList							
								,dtoList.size() + "건 조회 되었습니다.");												
	}
}
