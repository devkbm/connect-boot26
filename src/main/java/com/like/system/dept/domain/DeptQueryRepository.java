package com.like.system.dept.domain;

import java.util.List;

import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.boundary.DeptDTO.SearchDept;

public interface DeptQueryRepository {

	List<Dept> getDeptList(SearchDept searchCondition);
	
	List<ResponseDeptHierarchy> getDeptHierarchy();
}
