package com.like.system.biztypecode.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeQueryRepository;
import com.like.system.biztypecode.domain.QBizCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BizCodeQueryJpaRepository implements BizCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static final QBizCode qBizCode = QBizCode.bizCode;
		
	public BizCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<BizCode> getBizCodeList(String organizactionCode, String typeId) {	
		return queryFactory
				.selectFrom(qBizCode)
				.where(qBizCode.id.bizCodeTypeId.organizationCode.eq(organizactionCode)
				  .and(qBizCode.id.bizCodeTypeId.typeId.eq(typeId)))
				.fetch();
	}

}
