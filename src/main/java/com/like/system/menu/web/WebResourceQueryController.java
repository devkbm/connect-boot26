package com.like.system.menu.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.menu.boundary.WebResourceDTO;
import com.like.system.menu.boundary.WebResourceDTO.FormWebResource;
import com.like.system.menu.domain.WebResource;
import com.like.system.menu.service.WebResourceQueryService;

@RestController
public class WebResourceQueryController {

	private WebResourceQueryService service;
	
	public WebResourceQueryController(WebResourceQueryService service) {
		this.service = service; 
	}
	
	@GetMapping("/api/common/webresource")
	public ResponseEntity<?> getWebResourceList(WebResourceDTO.SearchWebResource condition) {							 			
		
		List<WebResource> list = service.getResourceList(condition);
										
		List<WebResourceDTO.FormWebResource> dtoList = list.stream()
														   .map(e -> FormWebResource.convertDTO(e))
														   .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())); 
	}
}
