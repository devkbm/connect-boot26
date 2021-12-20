package com.like.hrm.dutycode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DutyCodeRepository extends JpaRepository<DutyCode, String>, QuerydslPredicateExecutor<DutyCode> {

}
