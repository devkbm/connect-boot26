package com.like.cooperation.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArticleTest {

	@DisplayName("게시글 생성")
	@Test
	void createEntity() {
		// Given 			
		Board board = new Board(null, BoardType.BOARD, "테스트 게시판", "테스트 게시판입니다");
		// When
		Article article = new Article(board
									 ,new ArticleContents("제목", "내용")
									 ,new ArticlePassword("pwd")
									 ,null);
		
		// Then
		assertThat(article.getBoard()).isEqualTo(board);		
		assertThat(article.getContent().getTitle()).isEqualTo("제목");
		assertThat(article.getContent().getContents()).isEqualTo("내용");
		assertThat(article.getPassword().getPassword()).isEqualTo("pwd");				
	}
	
	@DisplayName("게시글 수정")
	@Test
	void modifyEntity() {
		// Given 			
		Board board = new Board(null, BoardType.BOARD, "테스트 게시판", "테스트 게시판입니다");
		Article article = new Article(board
									 ,new ArticleContents("제목", "내용")
									 ,new ArticlePassword("pwd")
									 ,null);		
		// When
		article.modifyEntity(new ArticleContents("제목 수정", "내용 수정"));				
		// Then
		assertThat(article.getBoard()).isEqualTo(board);		
		assertThat(article.getContent().getTitle()).isEqualTo("제목 수정");
		assertThat(article.getContent().getContents()).isEqualTo("내용 수정");
		assertThat(article.getPassword().getPassword()).isEqualTo("pwd");				
	}
	
}
