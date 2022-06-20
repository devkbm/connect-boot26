package com.like.system.dept.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.boundary.DeptDTO.Search;
import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptQueryRepository;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaQueryRepository implements DeptQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QDept qDept = QDept.dept;		
	
	public DeptJpaQueryRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Dept> getDeptList(Search searchCondition) {
		return queryFactory				
				.selectFrom(qDept)
				.where(searchCondition.getCondition())
				.fetch();
	}

	@Override
	public List<ResponseDeptHierarchy> getDeptHierarchy() {
		List<ResponseDeptHierarchy> rootNodeList = this.getDeptRootNodeList();
		
		List<ResponseDeptHierarchy> result = this.addDeptChildNodeList(rootNodeList);
		
		return result;
	}
	
	private List<ResponseDeptHierarchy> addDeptChildNodeList(List<ResponseDeptHierarchy> list) {
		List<ResponseDeptHierarchy> children = null;
		
		for ( ResponseDeptHierarchy node : list) {
			
			children = getDeptChildNodeList(node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(children);
			}			
		}
		
		return list;
	}

	private List<ResponseDeptHierarchy> getDeptRootNodeList() {
		return queryFactory
				.select(this.getDeptHierarchyConstructor())
				.from(qDept)
				.where(qDept.isRootNode())
				.orderBy(qDept.seq.asc())				
				.fetch();
	}
	
	private List<ResponseDeptHierarchy> getDeptChildNodeList(String parentDeptCode) {
		return queryFactory
				.select(this.getDeptHierarchyConstructor())
				.from(qDept)
				.where(qDept.parentDept.deptCode.eq(parentDeptCode))
				.orderBy(qDept.seq.asc())
				.fetch();
	}
	
	private ConstructorExpression<ResponseDeptHierarchy> getDeptHierarchyConstructor() {
		return Projections.constructor(
				ResponseDeptHierarchy.class,				
				qDept.parentDept.deptCode,
				qDept.deptCode,
				qDept.deptNameKorean,
				qDept.deptAbbreviationKorean,
				qDept.deptNameEnglish,
				qDept.deptAbbreviationEnglish,
				qDept.period,
				qDept.seq,
				qDept.comment);
	}

}
