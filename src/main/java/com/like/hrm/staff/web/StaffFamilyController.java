package com.like.hrm.staff.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.family.Family;
import com.like.hrm.staff.service.StaffFamilyService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffFamilyController {

	private StaffFamilyService service;
		
	public StaffFamilyController(StaffFamilyService service) {
		this.service = service;	
	}
	
	@GetMapping("/hrm/staff/{staffId}/family/{id}")
	public ResponseEntity<?> getFamily(@PathVariable String staffId
									  ,@PathVariable Long id) {
				
		Family entity = service.getFamily(staffId, id);  									
				
		StaffDTO.FormFamily dto = StaffDTO.FormFamily.convert(entity) ;
		
		return WebControllerUtil
				.getResponse(dto											
							,"%d 건 조회되었습니다.".formatted(dto == null ? 0 : 1));
							
	}
		
	@PostMapping("/hrm/staff/family")
	public ResponseEntity<?> saveFamily(@Valid @RequestBody StaffDTO.FormFamily dto) {			
							
		service.saveFamily(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,"1 건 저장되었습니다.");
	}
}
