package com.like.cooperation.board.domain;

import java.util.List;

import com.like.cooperation.board.boundary.BoardDTO;
import com.querydsl.core.types.Predicate;

public interface BoardQueryRepository {

	/**
	 * 전체 게시판 도메인 리스트를 조회
	 * @return	게시판 도메인 리스트
	 */
	List<Board> getBoardList(Predicate condition);
			
	/**
	 * 게시판 계층명단을 조회
	 * @return	
	 */
	List<BoardDTO.BoardHierarchy> getBoardHierarchy();
}
