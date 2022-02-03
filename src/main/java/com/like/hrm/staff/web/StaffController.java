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
import com.like.hrm.staff.service.StaffService;
import com.like.system.core.web.exception.ControllerException;
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
							,"%d 건 조회되었습니다.".formatted(dto == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/staff/create"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> newEmployee(@RequestBody @Valid StaffDTO.NewStaff dto, BindingResult result) {			
		
		if ( result.hasErrors()) throw new ControllerException("오류 : " + dto.toString());						
						
		staffService.newStaff(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,"%d 건 저장되었습니다.".formatted(1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/staff"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveStaff(@RequestBody StaffDTO.FormStaff dto, BindingResult result) {			
		
		if ( result.hasErrors()) throw new ControllerException("오류 : " + dto.toString());						
						
		staffService.saveStaff(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,"%d 건 저장되었습니다.".formatted(1)
							,HttpStatus.OK);
	}
			
}
