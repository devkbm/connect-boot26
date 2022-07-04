package com.like.system.dept.domain;

import java.util.List;

import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.boundary.DeptDTO.Search;

public interface DeptQueryRepository {

	List<Dept> getDeptList(Search searchCondition);
	
	List<ResponseDeptHierarchy> getDeptHierarchy(String organizationCode);
}
