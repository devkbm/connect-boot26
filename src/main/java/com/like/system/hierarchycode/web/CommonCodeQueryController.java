package com.like.system.hierarchycode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.message.MessageUtil;
import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.boundary.CodeHierarchy;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.SystemType;
import com.like.system.hierarchycode.service.CommonCodeQueryService;

@RestController
public class CommonCodeQueryController {

	private CommonCodeQueryService service;
	
	public CommonCodeQueryController(CommonCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/common/code/systemtype")
	public ResponseEntity<?> getWebResourceTypeList() {				
		
		List<HtmlSelectOptionRecord> list = new ArrayList<HtmlSelectOptionRecord>();
		
		for (SystemType type : SystemType.values()) {			
			list.add(new HtmlSelectOptionRecord(type.getDescription(), type.toString()));
		}
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/common/codetree") 
	public ResponseEntity<?> getCodeHierarchyList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<CodeHierarchy> list = service.getCodeHierarchyList(searchCondition);  						 						
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/common/code") 
	public ResponseEntity<?> getCodeList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<Code> list = service.getCodeList(searchCondition);  						 						
		
		List<CodeDTO.FormCode> dtoList = list.stream()
											 .map(e -> CodeDTO.FormCode.convertDTO(e))
											 .toList();
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
