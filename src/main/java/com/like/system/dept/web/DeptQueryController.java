package com.like.system.dept.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> getDeptHierarchyList(@ModelAttribute @Valid DeptDTO.Search searchCondition) {
							
		List<ResponseDeptHierarchy> list = service.getDeptHierarchyList(searchCondition.organizationCode());  						 						
		
		return toList(list, String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/common/dept")
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptDTO.Search searchCondition) {
							
		List<Dept> list = service.getDeptList(searchCondition);  						 						
		
		return toList(list, String.format("%d 건 조회되었습니다.", list.size()));
	}
}
