package com.like.hrm.hrmcode.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.boundary.SaveHrmRelationCode;
import com.like.hrm.hrmcode.boundary.HrmRelationCodeDTO.SearchHrmRelationCode;
import com.like.hrm.hrmcode.domain.HrmRelationCodeQueryRepository;
import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.like.hrm.hrmcode.domain.QHrmRelationCode;
//import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmRelationCodeQueryJpaRepository implements HrmRelationCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	//private static final QCode qCode = QCode.code1;
	private static final QHrmCodeType qHrmCodeType = QHrmCodeType.hrmCodeType;
	private static final QHrmCode qHrmCode = QHrmCode.hrmCode;
	private static final QHrmRelationCode qHrmRelationCode = QHrmRelationCode.hrmRelationCode;
	
	
	public HrmRelationCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}

	//@Override
	public List<SaveHrmRelationCode> getRelationCodeList(SearchHrmRelationCode condition) {
		QHrmCodeType qHrmType2 = QHrmCodeType.hrmCodeType;
		QHrmCode qHrmCode2 = QHrmCode.hrmCode;
		/*
		return queryFactory
				.select(new QSaveHrmRelationCode(
						qHrmRelationCode.relationId
					   ,qHrmRelationCode.relCode
					   ,qCode.codeName
					   ,qHrmRelationCode.parentTypeId
					   ,qHrmType.codeName
					   ,qHrmRelationCode.parentDetailId
					   ,qHrmTypeDetailCode.codeName
					   ,qHrmRelationCode.childTypeId
					   ,qHrmType2.codeName
					   ,qHrmRelationCode.childDetailId
					   ,qHrmTypeDetailCode2.codeName
						))
				.from(qHrmRelationCode)
				.join(qCode)
					.on(qHrmRelationCode.relCode.eq(qCode.id))
				.join(qHrmType)
					.on(qHrmRelationCode.parentTypeId.eq(qHrmType.id))
				.join(qHrmTypeDetailCode)
					.on(qHrmRelationCode.parentDetailId.eq(qHrmTypeDetailCode.id))
				.join(qHrmType2)
					.on(qHrmRelationCode.childTypeId.eq(qHrmType2.id))
				.join(qHrmTypeDetailCode2)
					.on(qHrmRelationCode.childDetailId.eq(qHrmTypeDetailCode2.id))
				.where(condition.getBooleanBuilder())
				.fetch();
				*/
		return null;
	}
	
	
}
