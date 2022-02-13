package com.like.hrm.staff.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.service.StaffService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffController {
	
	private StaffService staffService;
		
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}		
	
	@GetMapping("/hrm/staff/{id}")
	public ResponseEntity<?> getStaff(@PathVariable String id) {
								
		StaffDTO.ResponseStaff dto = StaffDTO.ResponseStaff.convert(staffService.getStaff(id)); 
		
		return WebControllerUtil
				.getResponse(dto											
							,"%d 건 조회되었습니다.".formatted(dto == null ? 0 : 1));
	}
		
	@PostMapping("/hrm/staff/create")
	public ResponseEntity<?> newStaff(@RequestBody @Valid StaffDTO.NewStaff dto) {											
						
		staffService.newStaff(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,"직원번호 : %s , 생성되었습니다.".formatted(dto.getStaffId()));
	}
		
	@PostMapping("/hrm/staff")
	public ResponseEntity<?> saveStaff(@RequestBody @Valid StaffDTO.FormStaff dto) {			
														
		staffService.saveStaff(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
						    ,"1 건 저장되었습니다.");
	}
			
}
