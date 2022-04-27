package com.like.cooperation.board.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleQueryRepository;
import com.like.cooperation.board.domain.QArticle;
import com.like.cooperation.board.domain.QArticleAttachedFile;
import com.like.system.file.domain.QFileInfo;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ArticleQueryJpaRepository implements ArticleQueryRepository {

	private JPAQueryFactory queryFactory;
	
	private final QArticle qArticle = QArticle.article;
	private final QFileInfo qFileInfo = QFileInfo.fileInfo;
	private final QArticleAttachedFile qAttachedFile = QArticleAttachedFile.articleAttachedFile;
	// private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;	
	
	public ArticleQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}	
	
	public List<Article> getArticleList(Long fkBoard) { 			
		
		return queryFactory.select(qArticle)
						   .from(qArticle)	
						   .leftJoin(qArticle.files, qAttachedFile)
						   .fetchJoin()
						   .where(qArticle.board.pkBoard.eq(fkBoard))							
						   .fetch();				
	}
	
	@Override
	public List<Article> getArticleList(Predicate condition) {
		return queryFactory.select(qArticle).distinct()
				   .from(qArticle)		  				   
				   .leftJoin(qArticle.files, qAttachedFile)
				   .fetchJoin()		  				   
				   .leftJoin(qAttachedFile.fileInfo, qFileInfo)
				   .fetchJoin()
				   .where(condition)
				   .orderBy(qArticle.pkArticle.desc())
				   .fetch();
	}

	
}
