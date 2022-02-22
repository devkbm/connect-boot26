package com.like.cooperation.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardQueryRepository;


@Service
@Transactional(readOnly=true)
public class BoardQueryService {
	
	private BoardQueryRepository boardRepository;        	
    
    public BoardQueryService(BoardQueryRepository boardRepository) {
    	this.boardRepository = boardRepository;    	
    }        
    
	public List<Board> getBoardList(BoardDTO.Search condition) {
		return boardRepository.getBoardList(condition.getBooleanBuilder());
	}	
		
	public List<?> getBoardHierarchy() {
		return boardRepository.getBoardHierarchy();
	}		
			
}
