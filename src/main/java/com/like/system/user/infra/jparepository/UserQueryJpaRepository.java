package com.like.system.user.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.user.boundary.UserDTO.SearchUser;
import com.like.system.user.domain.QSystemUser;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.UserQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserQueryJpaRepository implements UserQueryRepository {

	private JPAQueryFactory queryFactory;
	private static final QSystemUser qUser = QSystemUser.systemUser;	
	
	public UserQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}

	@Override
	public List<SystemUser> getUserList(SearchUser condition) {
		return  queryFactory
				.selectFrom(qUser)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
	
	
}
