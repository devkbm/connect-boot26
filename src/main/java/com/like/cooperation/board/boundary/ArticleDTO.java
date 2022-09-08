package com.like.cooperation.board.boundary;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleContents;
import com.like.cooperation.board.domain.ArticlePassword;
import com.like.cooperation.board.domain.QArticle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import static org.springframework.util.StringUtils.hasText;

public class ArticleDTO {
	
	public record Search(
			Long boardId,
			String title,
			String contents
			) {
		private static final QArticle qArticle = QArticle.article;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(qArticle.board.boardId.eq(this.boardId))
				.and(likeTitle(this.title))
				.and(likeContents(this.contents));											
			
			return builder;
		}
		
		private BooleanExpression likeTitle(String title) {
			return hasText(title) ? qArticle.content.title.like("%"+title+"%") : null;					
		}
		
		private BooleanExpression likeContents(String contents) {
			return hasText(contents) ? qArticle.content.contents.like("%"+contents+"%") : null;			
		}
	}	
	
	public record FormArticleByMuiltiPart(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String clientAppUrl,
			String organizationCode,
			Long boardId,
			String articleId,
			Long articleParentId,
			@NotEmpty(message="제목은 필수 입력 사항입니다.")
			String title,
			String contents,
			String pwd,
			int hitCount,						
			Integer depth,
			@JsonIgnore
			List<MultipartFile> file
			) {
		
		public FormArticleByMuiltiPart {			
		}
		
		public Article newArticle(Board board) {									    			
			Article entity = Article.builder()	
									.board(board)						  
									.content(new ArticleContents(title, contents))						  						  					 
									.password(new ArticlePassword(this.pwd))
									.build();
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
	    
	    public void modifyArticle(Article entity) {
	    	entity.modifyEntity(new ArticleContents(title, contents));
	    	
	    	entity.setAppUrl(clientAppUrl);
		}
	    
	    public boolean isNew() {
	    	return this.articleId() == null ? true : false;
	    }
	}	
	
	public record FormArticleByJson(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String clientAppUrl,
			String organizationCode,
			Long boardId,
			Long articleId,
			Long articleParentId,
			@NotEmpty(message="제목은 필수 입력 사항입니다.")
			String title,
			String contents,
			String pwd,
			int hitCount,			
			Integer seq,
			Integer depth,
			List<String> attachFile
			) {
		
		public Article newArticle(Board board) {				    				    	
			Article entity = Article.builder()	
								    .board(board)
								    .content(new ArticleContents(title, contents))						  						  
								    .password(new ArticlePassword(this.pwd))
								    .build();
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
	    
	    public void modifyArticle(Article entity) {	    		  	    	
	    	entity.modifyEntity(new ArticleContents(title, contents));
	    	
	    	entity.setAppUrl(clientAppUrl);
		}
	}	
		
	
}
