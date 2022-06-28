package com.like.hrm.staff.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.system.core.web.util.WebResponseUtil;

@RestController
public class StaffFormValidController {

	private StaffRepository repository;
	
	public StaffFormValidController(StaffRepository repository) {
		this.repository = repository;		
	}
	
	@GetMapping("/hrm/staff/{id}/valid")
	public ResponseEntity<?> isStaff(@PathVariable String id) {
		
		boolean exist = repository.existsById(id);
					
		return WebResponseUtil.toOne(exist											
											,exist == true ? "직원정보가 존재합니다." : "직원정보가 존재하지 않습니다.");
	}
	
	
}
