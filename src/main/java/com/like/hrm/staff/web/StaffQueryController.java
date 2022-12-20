package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;
import static com.like.system.core.web.util.ResponseEntityUtil.toMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.ResponseStaffCard;
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
	
	@GetMapping("/api/hrm/staff-card")
	public ResponseEntity<?> getStaffCardList() {
									
		List<?> list = service.getStaffCard();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff-card-group")
	public ResponseEntity<?> getStaffCardGroupList() {
									
		Map<String, List<ResponseStaffCard>> list = service.getStaffCard()
														   .stream()
														   .collect(Collectors.groupingBy(e -> e.getGroupName()));
		
		return toMap(list, MessageUtil.getQueryMessage(list.size()));
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
	
	@GetMapping("/api/hrm/staff/{id}/family")
	public ResponseEntity<?> getFamilyList(@PathVariable String id) {
		
		List<?> list = service.getStaffFamilyList(id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/schoolcareer")
	public ResponseEntity<?> getStaffSchoolCareer(@PathVariable String id) {
		List<?> list = service.getStaffSchoolCareer(id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/license")
	public ResponseEntity<?> getStaffLicense(@PathVariable String id) {
		List<?> list = service.getStaffLicense(id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
