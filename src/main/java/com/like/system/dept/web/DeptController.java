package com.like.system.dept.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.boundary.DeptDTO.FormDept;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.service.DeptService;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RestController
public class DeptController {
	
	private DeptService deptService;
	
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
		
	@GetMapping("/api/common/dept/{id}")
	public ResponseEntity<?> getDept(@PathVariable String id) {
							
		Dept dept = deptService.getDept(id);  	
		
		FormDept dto = DeptDTO.convertDTO(dept);
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
		
	//@RequestMapping(value={"/common/dept"}, method={RequestMethod.POST,RequestMethod.PUT})	
	@PostMapping("/api/common/dept")
	public ResponseEntity<?> saveDept(@RequestBody DeptDTO.FormDept dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 								
														
		deptService.saveDept(dto);		
											 				
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}		
	
	@DeleteMapping("/api/common/dept/{code}")
	public ResponseEntity<?> deleteDept(@PathVariable(value="code") String deptCode) {				
												
		deptService.deleteDept(deptCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
