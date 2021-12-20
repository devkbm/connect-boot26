package com.like.system.hierarchycode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonCodeRepository extends JpaRepository<Code, String>, QuerydslPredicateExecutor<Code> {		
		
}
