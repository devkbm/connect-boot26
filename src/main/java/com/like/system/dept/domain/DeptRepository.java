package com.like.system.dept.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, String>, QuerydslPredicateExecutor<Dept> {
				
	Dept findByDeptCode(String deptCode);
}