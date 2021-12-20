package com.like.cooperation.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCheckRepository extends JpaRepository<ArticleCheck, Long> {

	ArticleCheck findByCreatedByAndArticle(String userId, Article fkarticle);
	
}


/*
queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.createdBy.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
*/