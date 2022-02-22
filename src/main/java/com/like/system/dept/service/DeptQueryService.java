package com.like.system.dept.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptQueryRepository;

@Service
@Transactional(readOnly = true)
public class DeptQueryService {

	private DeptQueryRepository repository;
	
	public DeptQueryService(DeptQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<Dept> getDeptList(DeptDTO.Search searchCondition) {
		return repository.getDeptList(searchCondition);
	}
	
	public List<ResponseDeptHierarchy> getDeptHierarchyList() {
		return repository.getDeptHierarchy();
	}
	
}
