package com.like.system.holiday.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HolidayRepository extends JpaRepository<Holiday, HolidayId>, QuerydslPredicateExecutor<Holiday> {
	
}
