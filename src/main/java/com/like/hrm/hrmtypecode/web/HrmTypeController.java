package com.like.hrm.hrmtypecode.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeId;
import com.like.hrm.hrmtypecode.service.HrmTypeService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class HrmTypeController {

	private HrmTypeService service;			

	public HrmTypeController(HrmTypeService service) {
		this.service = service;		
	}							
	
	@GetMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable String id) {
		
		HrmTypeDTO.FormHrmType hrmType = service.getHrmTypeDTO(id);
					
		return ResponseEntityUtil.toOne(hrmType
									   ,MessageUtil.getQueryMessage(hrmType == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> saveHrmType(@RequestBody HrmTypeDTO.FormHrmType dto) {						
																	
		service.saveHrmType(dto);						
								 					
		return ResponseEntityUtil.toList(null
										,MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteHrmType(id);						
								 					
		return ResponseEntityUtil.toList(null
										,MessageUtil.getDeleteMessage(1));
	}			
	
	
	@GetMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable String type, @PathVariable String code) {
		
		HrmTypeDetailCodeDTO.FormHrmTypeDetailCode dto = service.getTypeDetailCodeDTO(new HrmTypeDetailCodeId(type, code));
					
		return ResponseEntityUtil.toOne(dto
									   ,MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmtype/{type}/code")
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmTypeDetailCodeDTO.FormHrmTypeDetailCode dto) {				
																			
		service.saveTypeDetailCode(dto);						
								 					
		return ResponseEntityUtil.toList(null	
										,MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable String type, @PathVariable String code) {				
																		
		service.deleteTypeDetailCode(new HrmTypeDetailCodeId(type, code));						
								 					
		return ResponseEntityUtil.toList(null		
										,MessageUtil.getDeleteMessage(1));
	}
	

	
	
}
