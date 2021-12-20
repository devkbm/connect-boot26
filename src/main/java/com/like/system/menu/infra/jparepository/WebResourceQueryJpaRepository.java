package com.like.system.menu.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.boundary.WebResourceDTO.SearchWebResource;
import com.like.system.menu.domain.QWebResource;
import com.like.system.menu.domain.WebResource;
import com.like.system.menu.domain.WebResourceQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WebResourceQueryJpaRepository implements WebResourceQueryRepository {

	private JPAQueryFactory queryFactory;	
	private final QWebResource qWebResource = QWebResource.webResource;
	
	public WebResourceQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<WebResource> getResourceList(SearchWebResource condition) {
		return queryFactory
				.selectFrom(qWebResource)
				.where(condition.getBooleanBuilder())
				.fetch();
	}	

	
}
