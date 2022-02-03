package com.like.hrm.staff.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.service.StaffQueryService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffQueryController {
	
	private StaffQueryService service;
	
	public StaffQueryController(StaffQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/hrm/staff")
	public ResponseEntity<?> getStaffList(StaffDTO.SearchStaff dto) {
		
		List<Staff> list = service.getEmployeeList(dto);					
		
		List<StaffDTO.ResponseStaff> dtoList = list.stream()
												   .map(e -> StaffDTO.ResponseStaff.convert(e))
												   .toList(); 
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/staff/{id}/record")
	public ResponseEntity<?> getStaffAppointmentRecordList(@PathVariable String id) {
		
		List<?> list = service.getStafflAppointmentRecordList(id);								
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
