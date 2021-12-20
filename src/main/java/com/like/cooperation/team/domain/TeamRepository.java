package com.like.cooperation.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TeamRepository extends JpaRepository<Team, Long> , QuerydslPredicateExecutor<Team> {

}
