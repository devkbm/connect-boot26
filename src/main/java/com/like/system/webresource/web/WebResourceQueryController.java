package com.like.system.webresource.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlOptionRecord;
import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.webresource.boundary.WebResourceDTO;
import com.like.system.webresource.boundary.WebResourceDTO.FormWebResource;
import com.like.system.webresource.domain.WebResource;
import com.like.system.webresource.domain.WebResourceType;
import com.like.system.webresource.service.WebResourceQueryService;

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
		
		return ResponseEntityUtil.toList(dtoList											
										,String.format("%d 건 조회되었습니다.", dtoList.size())); 
	}
	
	@GetMapping("/api/common/webresource/resourcetype")
	public ResponseEntity<?> getWebResourceTypeList() {				
		
		List<HtmlOptionRecord> list = new ArrayList<HtmlOptionRecord>();
		
		for (WebResourceType type : WebResourceType.values()) {			
			list.add(new HtmlOptionRecord(type.getLabel(), type.toString()));
		}
		
		return ResponseEntityUtil.toList(list											
										,String.format("%d 건 조회되었습니다.", list.size()));
	}
}
