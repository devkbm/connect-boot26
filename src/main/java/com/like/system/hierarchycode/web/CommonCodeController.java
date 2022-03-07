package com.like.system.hierarchycode.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.service.CommonCodeCommandService;

@RestController
public class CommonCodeController {
	
	private CommonCodeCommandService service;			
		
	public CommonCodeController(CommonCodeCommandService service) {
		this.service = service;		
	}	
	
	@GetMapping("/api/common/code/{id}") 
	public ResponseEntity<?> getCode(@PathVariable String id) {
								  						 					
		Code entity = service.getCode(id);
		
		CodeDTO.FormCode dto = CodeDTO.FormCode.convertDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto							
							,String.format("%d 건 조회되었습니다.", 1));
	}
			
	@PostMapping("/api/common/code")
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.FormCode dto) {					
		
		service.saveCode(dto);		
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1));
	}	
		
	@DeleteMapping("/api/common/code/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		service.deleteCode(id);
								 						
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 삭제되었습니다.", 1));
	}
	
	
}
