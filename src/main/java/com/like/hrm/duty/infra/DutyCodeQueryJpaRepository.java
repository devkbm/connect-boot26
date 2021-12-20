package com.like.hrm.duty.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.dutycode.boundary.DutyCodeDTO.SearchDutyCode;
import com.like.hrm.dutycode.domain.DutyCode;
import com.like.hrm.dutycode.domain.DutyCodeQueryRepository;
import com.like.hrm.dutycode.domain.QDutyCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DutyCodeQueryJpaRepository implements DutyCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public DutyCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<DutyCode> getDutyCodeList(SearchDutyCode condition) {
		return queryFactory
				.selectFrom(QDutyCode.dutyCode1)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
}
