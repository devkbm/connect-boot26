package com.like.cooperation.workschedule.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkGroupRepository extends JpaRepository<WorkGroup, Long>, QuerydslPredicateExecutor<WorkGroup> { 

}
