package com.like.cooperation.board.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardType;
import com.like.cooperation.board.service.BoardQueryService;
import com.like.system.core.dto.HtmlOptionRecord;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class BoardQueryController {

	private BoardQueryService boardQueryService;
	
	public BoardQueryController(BoardQueryService boardQueryService) {
		this.boardQueryService = boardQueryService;
	}
	
	@GetMapping("/api/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlOptionRecord> list = new ArrayList<>();
		
		for (BoardType boardType : BoardType.values()) {			
			list.add(new HtmlOptionRecord(boardType.getName(), boardType.toString()));
		}				 					
								
		return ResponseEntityUtil.toList(list				
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return ResponseEntityUtil.toList(list						
											,String.format("%d 건 조회되었습니다.", list.size()));
	}

	@GetMapping("/api/grw/board")
	public ResponseEntity<?> getBoardList(BoardDTO.Search dto) {						
		
		List<Board> list = boardQueryService.getBoardList(dto); 										
		List<BoardDTO.FormBoard> dtoList = list.stream()
											   .map(e -> BoardDTO.FormBoard.convertDTO(e))
											   .collect(Collectors.toList());
				
		return ResponseEntityUtil.toList(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
}
