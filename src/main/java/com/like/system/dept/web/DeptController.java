package com.like.system.dept.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.boundary.DeptDTO.FormDept;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.service.DeptService;

@RestController
public class DeptController {
	
	private DeptService deptService;
	private MessageSource messageSource;
	
	public DeptController(DeptService deptService, MessageSource messageSource) {
		this.deptService = deptService;
		this.messageSource = messageSource;
	}
		
	@GetMapping("/api/common/dept/{deptCode}")
	public ResponseEntity<?> getDept(@PathVariable String deptCode) {
							
		Dept dept = deptService.getDept(deptCode);  	
		
		FormDept dto = DeptDTO.FormDept.convertDTO(dept);
		
		return ResponseEntityUtil.toOne(dto											
									   ,messageSource.getMessage("common.query", new Integer[] {1}, Locale.getDefault()));
	}
		
	@PostMapping("/api/common/dept")
	public ResponseEntity<?> saveDept(@Valid @RequestBody DeptDTO.FormDept dto) {			
																
		deptService.saveDept(dto);		
											 				
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}		
	
	@DeleteMapping("/api/common/dept/{deptCode}")
	public ResponseEntity<?> deleteDept(@PathVariable String deptCode) {				
												
		deptService.deleteDept(deptCode);							
		
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}
	
}
