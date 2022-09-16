package com.like.system.dept.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
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
		
	@GetMapping("/api/system/dept/{deptCode}")
	public ResponseEntity<?> getDept(@PathVariable String deptCode, @RequestParam String organizationCode) {
							
		Dept dept = deptService.getDept(organizationCode + deptCode);  	
		
		FormDept dto = DeptDTO.FormDept.convertDTO(dept);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/system/dept")
	public ResponseEntity<?> saveDept(@Valid @RequestBody DeptDTO.FormDept dto) {			
																
		deptService.saveDept(dto);		
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}		
	
	@DeleteMapping("/api/system/dept/{deptCode}")
	public ResponseEntity<?> deleteDept(@PathVariable String deptCode) {				
												
		deptService.deleteDept(deptCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
