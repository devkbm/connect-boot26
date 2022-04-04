package com.like.hrm.duty.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.QDutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationQueryRepository;
import com.like.hrm.dutycode.domain.DutyCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DutyApplicationQueryJpaRepository implements DutyApplicationQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public DutyApplicationQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.Search condition) {
		return queryFactory
				.selectFrom(QDutyApplication.dutyApplication)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public long getDutyApplicationCount(String staffId, List<DutyCode> dutyCodeList, LocalDate fromDate,
			LocalDate toDate) {
		
		QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;							
		List<String> dutyCodes = dutyCodeList.stream().map(e -> e.getDutyCode()).toList();
				
		return queryFactory
				.selectFrom(QDutyApplication.dutyApplication)
				.where(qDutyApplication.staffId.eq(staffId)
				  .and(qDutyApplication.dutyCode.in(dutyCodes)))
				.fetch()
				.size();				
	}

}
