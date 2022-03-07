package com.like.system.menu.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.menu.boundary.WebResourceDTO;
import com.like.system.menu.boundary.WebResourceDTO.FormWebResource;
import com.like.system.menu.domain.WebResource;
import com.like.system.menu.service.WebResourceService;

@RestController
public class WebResourceController {

	private WebResourceService service;
	
	public WebResourceController(WebResourceService menuCommandService) {
		this.service = menuCommandService;		
	}
	
	@GetMapping("/api/common/webresource/{code}")
	public ResponseEntity<?> getResource(@PathVariable String code) {				
		
		WebResource resource = service.getResource(code); 							
		
		WebResourceDTO.FormWebResource dto = FormWebResource.convertDTO(resource);
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0));
	}
		
	@PostMapping("/api/common/webresource")
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceDTO.FormWebResource dto) throws Exception {
																												
		service.saveWebResource(dto);																						
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/common/webresource/{code}")
	public ResponseEntity<?> delResource(@PathVariable(value="code") String code) {				
												
		service.deleteWebResource(code);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1));
	}
}
