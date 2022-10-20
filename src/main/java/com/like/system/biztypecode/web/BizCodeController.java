package com.like.system.biztypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.boundary.BizCodeDTO;
import com.like.system.biztypecode.service.BizCodeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeController {

	private BizCodeService service;
	
	public BizCodeController(BizCodeService service) {
		this.service = service;
	}
		
	@GetMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> getBizCode(@RequestParam String organizationCode
									   ,@PathVariable String typeId
									   ,@PathVariable String code) {
		
		BizCodeDTO.Form dto = BizCodeDTO.Form.convert(service.getBizCode(organizationCode, typeId, code));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/bizcodetype/bizcode")	
	public ResponseEntity<?> saveBizCode(@RequestBody BizCodeDTO.Form dto) {				
																		
		service.saveBizCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> deleteBizCode(@RequestParam String organizationCode
										  ,@PathVariable String typeId
			 							  ,@PathVariable String code) {				
																		
		service.deleteBizCode(organizationCode, typeId, code);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
