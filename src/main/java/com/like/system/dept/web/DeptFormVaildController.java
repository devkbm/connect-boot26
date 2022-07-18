package com.like.system.dept.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.dept.service.DeptService;

@RestController
public class DeptFormVaildController {
	
	private DeptService deptService;
	
	public DeptFormVaildController(DeptService deptService) {
		this.deptService = deptService;		
	}
	
	@GetMapping("/api/common/dept/{id}/valid")
	public ResponseEntity<?> getValidateDeptDuplication(@PathVariable String id) {
							
		Boolean exist = deptService.isDept(id);  	
						
		return toOne(exist, exist ? "중복된 부서 코드가 있습니다." : "사용가능한 부서 코드입니다.");
	}
	
}
