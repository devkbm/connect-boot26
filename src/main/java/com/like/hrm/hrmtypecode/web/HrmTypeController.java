package com.like.hrm.hrmtypecode.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeId;
import com.like.hrm.hrmtypecode.service.HrmTypeService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebResponseUtil;

@RestController
public class HrmTypeController {

	private HrmTypeService service;			

	public HrmTypeController(HrmTypeService service) {
		this.service = service;		
	}							
	
	@GetMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable(value="id") String id) {
		
		HrmTypeDTO.FormHrmType hrmType = service.getHrmTypeDTO(id);
					
		return WebResponseUtil.toOne(hrmType											
											,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmtype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmType(@RequestBody HrmTypeDTO.FormHrmType dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveHrmType(dto);						
								 					
		return WebResponseUtil.toList(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteHrmType(id);						
								 					
		return WebResponseUtil.toList(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}			
	
	
	@GetMapping("/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable(value="type") String type, @PathVariable(value="code") String code) {
		
		HrmTypeDetailCodeDTO.FormHrmTypeDetailCode dto = service.getTypeDetailCodeDTO(new HrmTypeDetailCodeId(type, code));
					
		return WebResponseUtil.toOne(dto
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmtype/{type}/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmTypeDetailCodeDTO.FormHrmTypeDetailCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveTypeDetailCode(dto);						
								 					
		return WebResponseUtil.toList(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable(value="type") String type, @PathVariable(value="code") String code) {				
																		
		service.deleteTypeDetailCode(new HrmTypeDetailCodeId(type, code));						
								 					
		return WebResponseUtil.toList(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	

	
	
}
