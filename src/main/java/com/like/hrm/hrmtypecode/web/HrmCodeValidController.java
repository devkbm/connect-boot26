package com.like.hrm.hrmtypecode.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeId;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeRepository;
import com.like.hrm.hrmtypecode.domain.HrmTypeRepository;
import com.like.system.core.web.util.WebResponseUtil;

@RestController
public class HrmCodeValidController {
	
	private HrmTypeRepository repository;
	private HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository;
	
	public HrmCodeValidController(HrmTypeRepository repository
								 ,HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository) {
		this.repository = repository;
		this.hrmTypeDetailCodeRepository = hrmTypeDetailCodeRepository;
	}
	
	@GetMapping("/hrm/hrmtype/{id}/valid")
	public ResponseEntity<?> validHrmType(@PathVariable(value="id") String id) {
		
		boolean exist = repository.existsById(id);
					
		return WebResponseUtil.toOne(exist											
											,exist ? "중복된 인사유형 코드가 있습니다." : "사용가능한 코드입니다."
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/hrmtype/{type}/{code}/valid")
	public ResponseEntity<?> validHrmCode(@PathVariable(value="type") String type, @PathVariable(value="code") String code) {
		
		boolean exist = hrmTypeDetailCodeRepository.existsById(new HrmTypeDetailCodeId(type, code));
					
		return WebResponseUtil.toOne(exist
											,exist ? "중복된 인사유형 코드가 있습니다." : "사용가능한 코드입니다."
											,HttpStatus.OK);
	}
	
	/*
	@GetMapping("/common/dept/{id}/valid")
	public ResponseEntity<?> getValidateDeptDuplication(@PathVariable String id) {
							
		Boolean exist = deptService.isDept(id);  	
						
		return WebControllerUtil
				.getResponse(exist								
							,exist ? "중복된 부서 코드가 있습니다." : "사용가능한 부서 코드입니다." 
							,HttpStatus.OK);
	}
	
	 * 
	 */
}
