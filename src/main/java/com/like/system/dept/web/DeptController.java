package com.like.system.dept.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebResponseUtil;
import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.boundary.DeptDTO.FormDept;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.service.DeptService;

@RestController
public class DeptController {
	
	private DeptService deptService;
	
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
		
	@GetMapping("/api/common/dept/{deptCode}")
	public ResponseEntity<?> getDept(@PathVariable String deptCode) {
							
		Dept dept = deptService.getDept(deptCode);  	
		
		FormDept dto = DeptDTO.FormDept.convertDTO(dept);
		
		return WebResponseUtil.toOne(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/common/dept")
	public ResponseEntity<?> saveDept(@Valid @RequestBody DeptDTO.FormDept dto) {			
																
		deptService.saveDept(dto);		
											 				
		return WebResponseUtil.toList(null											
											,String.format("%d 건 저장되었습니다.", 1));
	}		
	
	@DeleteMapping("/api/common/dept/{deptCode}")
	public ResponseEntity<?> deleteDept(@PathVariable String deptCode) {				
												
		deptService.deleteDept(deptCode);							
		
		return WebResponseUtil.toList(null											
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
}
