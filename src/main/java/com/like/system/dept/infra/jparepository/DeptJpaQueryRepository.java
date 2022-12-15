package com.like.system.dept.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.boundary.DeptDTO.Search;
import com.like.system.dept.boundary.ResponseDeptHierarchy;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptQueryRepository;
import com.like.system.dept.domain.QDept;
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
	public List<ResponseDeptHierarchy> getDeptHierarchy(String organizationCode) {
		List<ResponseDeptHierarchy> rootNodeList = this.getDeptRootNodeList(organizationCode);
		
		List<ResponseDeptHierarchy> result = this.addDeptChildNodeList(organizationCode, rootNodeList);
		
		return result;
	}
	
	private List<ResponseDeptHierarchy> addDeptChildNodeList(String organizationCode, List<ResponseDeptHierarchy> list) {
		List<ResponseDeptHierarchy> children = null;
		
		for ( ResponseDeptHierarchy node : list) {
			
			children = getDeptChildNodeList(organizationCode, node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(organizationCode, children);
			}			
		}
		
		return list;
	}

	private List<ResponseDeptHierarchy> getDeptRootNodeList(String organizationCode) {
		return queryFactory
				.select(ResponseDeptHierarchy.projection(qDept))				
				.from(qDept)
				.where(qDept.organizationCode.eq(organizationCode), qDept.isRootNode())
				.orderBy(qDept.seq.asc())				
				.fetch();
	}
	
	private List<ResponseDeptHierarchy> getDeptChildNodeList(String organizationCode, String parentDeptCode) {
		return queryFactory
				.select(ResponseDeptHierarchy.projection(qDept))
				.from(qDept)
				.where(qDept.organizationCode.eq(organizationCode), qDept.parentDept.deptCode.eq(parentDeptCode))
				.orderBy(qDept.seq.asc())
				.fetch();
	}
		
}
