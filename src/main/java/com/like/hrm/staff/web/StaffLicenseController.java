package com.like.hrm.staff.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.license.License;
import com.like.hrm.staff.service.StaffLicenseService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class StaffLicenseController {

	private StaffLicenseService service;
		
	public StaffLicenseController(StaffLicenseService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/staff/{staffId}/license/{id}")
	public ResponseEntity<?> getLicense(@PathVariable String staffId,
										@PathVariable Long id) {
				
		License license = service.getLicense(staffId, id);  									
		
		return ResponseEntityUtil.toOne(license	
						    		   ,"%d 건 조회되었습니다.".formatted(license == null ? 0 : 1));
	}
		
	@PostMapping("/hrm/employee/license")
	public ResponseEntity<?> saveLicense(@Valid @RequestBody StaffDTO.FormLicense dto) {						
				
		service.saveLicense(dto);
											 				
		return ResponseEntityUtil.toList(null							
										,"1 건 저장되었습니다.");
	}
}
