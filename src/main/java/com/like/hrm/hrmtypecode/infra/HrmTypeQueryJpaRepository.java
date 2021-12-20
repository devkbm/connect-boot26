package com.like.hrm.hrmtypecode.infra;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO.SearchHrmType;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode;
import com.like.hrm.hrmtypecode.domain.HrmType;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCode;
import com.like.hrm.hrmtypecode.domain.HrmTypeQueryRepository;
import com.like.hrm.hrmtypecode.domain.QHrmType;
import com.like.hrm.hrmtypecode.domain.QHrmTypeDetailCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmTypeQueryJpaRepository implements HrmTypeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static final QHrmType qHrmType = QHrmType.hrmType;
	private static final QHrmTypeDetailCode qHrmTypeDetailCode = QHrmTypeDetailCode.hrmTypeDetailCode;
	
	public HrmTypeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<HrmType> getHrmTypeList(SearchHrmType condition) {
		return queryFactory
				.selectFrom(qHrmType)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<HrmTypeDetailCode> getTypeDetailCodeList(SearchHrmTypeDetailCode condition) {
		return queryFactory
				.selectFrom(qHrmTypeDetailCode)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

}
