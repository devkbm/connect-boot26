package com.like.system.hierarchycode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.service.CommonCodeCommandService;

@RestController
public class CommonCodeController {
	
	private CommonCodeCommandService service;			
		
	public CommonCodeController(CommonCodeCommandService service) {
		this.service = service;		
	}	
	
	@GetMapping("/api/system/code/{systemtype}/{codeId}") 
	public ResponseEntity<?> getCode(@PathVariable String systemtype
									,@PathVariable String codeId) {
								  						 					
		Code entity = service.getCode(systemtype, codeId);
		
		CodeDTO.Form dto = CodeDTO.Form.convertDTO(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/code")
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.Form dto) {					
		
		service.saveCode(dto);		
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/api/system/code/{systemtype}/{codeId}")
	public ResponseEntity<?> delCode(@PathVariable String systemtype
									,@PathVariable String codeId) {						
												
		service.deleteCode(systemtype, codeId);
								 						
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	
}
