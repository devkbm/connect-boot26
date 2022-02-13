package com.like.hrm.staff.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareer;
import com.like.hrm.staff.service.StaffSchoolCareerService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffSchoolCareerController {

	private StaffSchoolCareerService service;
		
	public StaffSchoolCareerController(StaffSchoolCareerService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/staff/{staffId}/education/{id}")
	public ResponseEntity<?> getEducation(@PathVariable String staffId,
										  @PathVariable Long id) {
				
		SchoolCareer education = service.getSchoolCareer(staffId, id);  									
		
		return WebControllerUtil
				.getResponse(education											
							,"%d 건 조회되었습니다.".formatted(education == null ? 0 : 1));
	}
		
	@PostMapping("/hrm/staff/education")
	public ResponseEntity<?> saveEducation(@RequestBody @Valid StaffDTO.FormEducation dto) {					
				
		service.saveSchoolCareer(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,"1 건 저장되었습니다.");
	}
	
}
