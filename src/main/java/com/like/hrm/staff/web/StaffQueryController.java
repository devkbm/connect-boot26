package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.service.StaffQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffQueryController {
	
	private StaffQueryService service;
	
	public StaffQueryController(StaffQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/hrm/staff")
	public ResponseEntity<?> getStaffList(StaffDTO.SearchStaff dto) {
									
		List<StaffDTO.ResponseStaff> list = service.getStaff(dto)
												   .stream()
												   .map(e -> StaffDTO.ResponseStaff.convert(e))
												   .toList(); 
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/record")
	public ResponseEntity<?> getStaffAppointmentRecordList(@PathVariable String id) {
		
		List<?> list = service.getStaffAppointmentRecordList(id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/currentappointment")
	public ResponseEntity<?> getStaffCurrentAppointment(@PathVariable String id) {
		
		ResponseStaffCurrentAppointment dto = service.getStaffCurrentAppointment(id);								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
}
