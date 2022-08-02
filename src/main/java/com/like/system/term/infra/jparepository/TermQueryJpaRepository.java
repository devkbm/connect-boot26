package com.like.system.term.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.TermQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermQueryJpaRepository implements TermQueryRepository {
	
	private final QTermDictionary qTermDictionary = QTermDictionary.termDictionary;
		
	private JPAQueryFactory  queryFactory;		

	public TermQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}	
		
	@Override
	public List<TermDictionary> getTermList(TermDTO.Search condition) {									
		return queryFactory.selectFrom(qTermDictionary)
						   .where(condition.getBooleanBuilder())
						   .fetch();
	}

}
