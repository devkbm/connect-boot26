package com.like.system.hierarchycode.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.boundary.CodeHierarchy;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.service.CommonCodeQueryService;

@RestController
public class CommonCodeQueryController {

	private CommonCodeQueryService service;
	
	public CommonCodeQueryController(CommonCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/common/codetree") 
	public ResponseEntity<?> getCodeHierarchyList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<CodeHierarchy> list = service.getCodeHierarchyList(searchCondition);  						 						
		
		return WebControllerUtil
				.getResponse(list							
							,String.format("%d 건 조회되었습니다.", list.size())
							,HttpStatus.OK);
	}
	
	@GetMapping("/api/common/code") 
	public ResponseEntity<?> getCodeList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<Code> list = service.getCodeList(searchCondition);  						 						
		
		List<CodeDTO.FormCode> dtoList = list.stream()
											 .map(e -> CodeDTO.FormCode.convertDTO(e))
											 .toList();
		
		return WebControllerUtil
				.getResponse(dtoList							
							,String.format("%d 건 조회되었습니다.", dtoList.size())
							,HttpStatus.OK);
	}
}
