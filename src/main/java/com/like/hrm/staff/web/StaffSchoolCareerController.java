package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

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
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSchoolCareerController {

	private StaffSchoolCareerService service;
		
	public StaffSchoolCareerController(StaffSchoolCareerService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/education/{id}")
	public ResponseEntity<?> getEducation(@PathVariable String staffId,
										  @PathVariable Long id) {
				
		SchoolCareer schoolCareer = service.getSchoolCareer(staffId, id);  									
		
		return toOne(schoolCareer, MessageUtil.getQueryMessage(schoolCareer == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/staff/education")
	public ResponseEntity<?> saveEducation(@RequestBody @Valid StaffDTO.FormEducation dto) {					
				
		service.saveSchoolCareer(dto);
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
}
