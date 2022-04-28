package com.like.cooperation.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

	@DisplayName("게시판 생성")
	@Test
	void createEntity() {
		// Given 			
		// When
		Board board = new Board(null, BoardType.BOARD, "테스트 게시판", "테스트 게시판입니다");
		
		// Then
		assertThat(board.getBoardType()).isEqualTo(BoardType.BOARD);
		assertThat(board.getBoardName()).isEqualTo("테스트 게시판");
		assertThat(board.getDescription()).isEqualTo("테스트 게시판입니다");
		assertThat(board.getUseYn()).isEqualTo(true);		
	}
	
	@DisplayName("게시판 수정")
	@Test
	void modifyEntity() {
		// Given
		Board board = new Board(null, BoardType.BOARD, "테스트 게시판", "테스트 게시판입니다");		
		// When
		board.modifyEntity(null, BoardType.NOTICE, "수정 게시판", "수정 게시판입니다", false, 1L);
		// Then
		assertThat(board.getBoardType()).isEqualTo(BoardType.NOTICE);
		assertThat(board.getBoardName()).isEqualTo("수정 게시판");
		assertThat(board.getDescription()).isEqualTo("수정 게시판입니다");
		assertThat(board.getUseYn()).isEqualTo(false);				
	}		
	
	@DisplayName("게시판 즐겨찾기 등록")
	@Test
	void createBookMark() {
		// Given
		Board board = new Board(null, BoardType.BOARD, "테스트 게시판", "테스트 게시판입니다");
		String userId = "test";
		
		// When		
		board.addBookmark(userId);			
		
		BoardBookmark result = board.getBookmarks().iterator().next();
		
		// Then
		assertThat(result.getBoard()).isEqualTo(board);
		assertThat(result.getUserId()).isEqualTo("test");
		assertThat(result.getSeq()).isEqualTo(0L);
	}
}
