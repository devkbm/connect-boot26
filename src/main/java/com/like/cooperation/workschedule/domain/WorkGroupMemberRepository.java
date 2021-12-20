package com.like.cooperation.workschedule.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkGroupMemberRepository extends JpaRepository<WorkGroupMember, WorkGroupMemberId>, QuerydslPredicateExecutor<WorkGroupMember> { 

}
