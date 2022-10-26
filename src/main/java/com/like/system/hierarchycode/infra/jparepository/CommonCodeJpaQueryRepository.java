package com.like.system.hierarchycode.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.hierarchycode.boundary.CodeComboDTO;
import com.like.system.hierarchycode.boundary.CodeDTO.Search;
import com.like.system.hierarchycode.boundary.CodeHierarchy;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CommonCodeQueryRepository;
import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CommonCodeJpaQueryRepository implements CommonCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private final QCode qCode = QCode.code1;
			
	public CommonCodeJpaQueryRepository(JPAQueryFactory	queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Code> getCodeList(String parentCodeId) {
		return queryFactory
				.selectFrom(qCode)
				.where(qCode.parentCode.id.codeId.eq(parentCodeId))
				.fetch();
	}
	
	@Override
	public List<Code> getCodeList(Predicate predicate) {
		return queryFactory
				.selectFrom(qCode)
				.where(predicate)
				.fetch();
	}

	@Override
	public List<CodeHierarchy> getCodeHierarchyList(Search dto) {
		List<CodeHierarchy> rootNodeList = this.getCodeRootNodeList(dto.systemTypeCode());
		
		List<CodeHierarchy> result = this.addCodeChildrenList(rootNodeList);
		
		return result;
	}

	@Override
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return null; /* queryFactory
		.select(Projections.constructor(CodeComboDTO.class, qCommonCode.id.code,qCommonCode.codeName,qCommonCode.codeNameAbbreviation))
		.from(qCommonCode)
		.where(qCommonCode.id.codeGroup.eq(codeGroup))
		.fetch();*/
	}

	private List<CodeHierarchy> getCodeRootNodeList(String systemTypeCode) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qCode.isRootNode())
		       .and(qCode.enabled())
		       .and(qCode.id.systemTypeCode.eq(systemTypeCode));				
				
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)			
				.orderBy(qCode.seq.asc())
				.fetch();
	}
		
	private List<CodeHierarchy> getCodeChildNodeList(String parentCodeId) {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(qCode.parentCode.id.codeId.eq(parentCodeId))
			.and(qCode.enabled());
			
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)
				.orderBy(qCode.seq.asc())
				.fetch();
	}
	
	/**
	 * 하위 노드를 검색후 하위 노드가 존재하면 상위 노드에 하위 노드 추가함(재귀 함수)
	 * @param list
	 * @return
	 */
	private List<CodeHierarchy> addCodeChildrenList(List<CodeHierarchy> list) {
		List<CodeHierarchy> children = null;
		
		for ( CodeHierarchy code : list ) {
			
			children = getCodeChildNodeList(code.getId());
			
			if (children.isEmpty()) {
				code.setLeaf(true);
				continue;
			} else {
				code.setChildren(children);
				code.setLeaf(false);
				
				// 재귀 호출
				addCodeChildrenList(children);
			}				
		}
		
		return list;
	}
	
	/*
	 * 	public CodeHierarchy(String codeId, String systemTypeCode, String code, String codeName, String codeNameAbbreviation, String parentId, 
						 LocalDateTime fromDate, LocalDateTime toDate, int seq, String cmt)
	 */
	
	private ConstructorExpression<CodeHierarchy> getCodehierarchyConstructor() {		
		return Projections.constructor(
				CodeHierarchy.class,	
				qCode.id.codeId,
				qCode.id.systemTypeCode,				
				qCode.code, 
				qCode.codeName, 
				qCode.codeNameAbbreviation,
				qCode.parentCode.id.codeId,
				qCode.fromDate, 
				qCode.toDate, 
				qCode.seq, 
				qCode.cmt);		
	}

}
