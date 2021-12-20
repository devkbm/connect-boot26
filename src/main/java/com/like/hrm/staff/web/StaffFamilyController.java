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
import com.like.hrm.staff.domain.model.family.Family;
import com.like.hrm.staff.service.StaffFamilyService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffFamilyController {

	private StaffFamilyService service;
		
	public StaffFamilyController(StaffFamilyService service) {
		this.service = service;	
	}
	
	@GetMapping("/hrm/employee/{empId}/family/{id}")
	public ResponseEntity<?> getFamily(@PathVariable String empId
									  ,@PathVariable Long id) {
				
		Family entity = service.getFamily(empId, id);  									
				
		StaffDTO.FormFamily dto = StaffDTO.FormFamily.convert(entity) ;
		
		return WebControllerUtil
				.getResponse(dto											
							,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/family"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveFamily(@RequestBody StaffDTO.FormFamily dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		service.saveFamily(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
}
