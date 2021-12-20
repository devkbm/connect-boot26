package com.like.system.dept.domain;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class DeptExpression {

	@QueryDelegate(Dept.class)
	public static BooleanExpression isRootNode(QDept dept) {
		return dept.parentDept.isNull();
	}
	
	@QueryDelegate(Dept.class)
	public static BooleanExpression isChildNode(QDept dept) {
		return dept.parentDept.isNotNull();
	}
}
