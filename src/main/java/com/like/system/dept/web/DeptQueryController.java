package com.like.system.dept.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebResponseUtil;
import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.service.DeptQueryService;

@RestController
public class DeptQueryController {

	private DeptQueryService service;
	
	public DeptQueryController(DeptQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/common/depttree")
	public ResponseEntity<?> getDeptHierarchyList(@ModelAttribute DeptDTO.Search searchCondition) {
							
		List<ResponseDeptHierarchy> list = service.getDeptHierarchyList();  						 						
		
		return WebResponseUtil.toList(list											
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/common/dept")
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptDTO.Search searchCondition) {
							
		List<Dept> list = service.getDeptList(searchCondition);  						 						
		
		return WebResponseUtil.toList(list											
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
}
