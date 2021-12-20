package com.like.hrm.staff.web;

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
import com.like.hrm.staff.domain.model.license.License;
import com.like.hrm.staff.service.StaffLicenseService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffLicenseController {

	private StaffLicenseService service;
		
	public StaffLicenseController(StaffLicenseService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/employee/{empId}/license/{id}")
	public ResponseEntity<?> getLicense(@PathVariable String empId,
										@PathVariable Long id) {
				
		License license = service.getLicense(empId, id);  									
		
		return WebControllerUtil
				.getResponse(license											
							,String.format("%d 건 조회되었습니다.", license == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/license"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveLicense(@RequestBody StaffDTO.FormLicense dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		service.saveLicense(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
}
