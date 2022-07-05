package com.like.system.user.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.user.boundary.SystemUserDTO.Search;
import com.like.system.user.domain.QSystemUser;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SystemUserQueryJpaRepository implements SystemUserQueryRepository {

	private JPAQueryFactory queryFactory;
	private static final QSystemUser qUser = QSystemUser.systemUser;	
	
	public SystemUserQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}

	@Override
	public List<SystemUser> getUserList(Search condition) {
		return  queryFactory
				.selectFrom(qUser)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
	
	
}
