package com.like.cooperation.workschedule.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.QWorkGroup;
import com.like.cooperation.workschedule.domain.QWorkGroupMember;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroupQueryRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WorkGroupQueryJpaRepository implements WorkGroupQueryRepository {
	private JPAQueryFactory queryFactory;
	private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
	private final QWorkGroupMember qWorkGroupMember = QWorkGroupMember.workGroupMember;
	
	public WorkGroupQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkGroup> getWorkGroupList(WorkDTO.Search searchCondition) {
		return queryFactory
				.selectFrom(qWorkGroup)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<WorkGroup> getWorkGroupList(String userId) {

		return queryFactory
				.selectFrom(qWorkGroup)
				.join(qWorkGroup.memberList, qWorkGroupMember)
				.where(qWorkGroupMember.user.id.eq(userId))
				.fetch();
	}
	
	
}
