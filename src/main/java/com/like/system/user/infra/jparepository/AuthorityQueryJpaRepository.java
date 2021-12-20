package com.like.system.user.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.user.boundary.AuthorityDTO.SearchAuthority;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.AuthorityQueryRepository;
import com.like.system.user.domain.QAuthority;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AuthorityQueryJpaRepository implements AuthorityQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QAuthority qAuthority = QAuthority.authority;	
	
	public AuthorityQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Authority> getAuthorityList(SearchAuthority condition) {
		return queryFactory
				.selectFrom(qAuthority)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

}
