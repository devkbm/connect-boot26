package com.like.cooperation.workschedule.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.domain.QSchedule;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.domain.ScheduleQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ScheduleQueryJpaRepository implements ScheduleQueryRepository {
	private JPAQueryFactory queryFactory;
	private final QSchedule qSchedule = QSchedule.schedule;
	
	public ScheduleQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Schedule> getScheduleList(ScheduleDTO.Search searchCondition) {
		return queryFactory
				.selectFrom(qSchedule)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
}
