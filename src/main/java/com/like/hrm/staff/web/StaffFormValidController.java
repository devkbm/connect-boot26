package com.like.hrm.staff.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffFormValidController {

	private StaffRepository repository;
	
	public StaffFormValidController(StaffRepository repository) {
		this.repository = repository;		
	}
	
	@GetMapping("/hrm/employee/{id}/valid")
	public ResponseEntity<?> getEmployee(@PathVariable String id) {
		
		boolean exist = repository.existsById(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist == true ? "직원정보가 존재합니다." : "직원정보가 존재하지 않습니다."
											,HttpStatus.OK);
	}
	
	
}
