package com.like.hrm.staff.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareer;
import com.like.hrm.staff.service.StaffSchoolCareerService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffSchoolCareerController {

	private StaffSchoolCareerService service;
		
	public StaffSchoolCareerController(StaffSchoolCareerService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/employee/{empId}/education/{id}")
	public ResponseEntity<?> getEducation(@PathVariable String empId,
										  @PathVariable Long id) {
				
		SchoolCareer education = service.getSchoolCareer(empId, id);  									
		
		return WebControllerUtil
				.getResponse(education											
							,String.format("%d 건 조회되었습니다.", education == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/education"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveEducation(@RequestBody @Valid StaffDTO.FormEducation dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		service.saveSchoolCareer(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
}
