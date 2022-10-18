package com.like.hrm.hrmtypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

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

@RestController
public class HrmTypeController {

	private HrmTypeService service;			

	public HrmTypeController(HrmTypeService service) {
		this.service = service;		
	}							
	
	@GetMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable String id) {
		
		HrmTypeDTO.Form hrmType = service.getHrmTypeDTO(id);
					
		return toOne(hrmType, MessageUtil.getQueryMessage(hrmType == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> saveHrmType(@RequestBody HrmTypeDTO.Form dto) {						
																	
		service.saveHrmType(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteHrmType(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}			
	
	
	@GetMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable String type, @PathVariable String code) {
		
		HrmTypeDetailCodeDTO.Form dto = service.getTypeDetailCodeDTO(new HrmTypeDetailCodeId(type, code));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmtype/{type}/code")
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmTypeDetailCodeDTO.Form dto) {				
																			
		service.saveTypeDetailCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable String type, @PathVariable String code) {				
																		
		service.deleteTypeDetailCode(new HrmTypeDetailCodeId(type, code));						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	

	
	
}
