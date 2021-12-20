package com.like.cooperation.board.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardType;
import com.like.cooperation.board.service.BoardQueryService;
import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.menu.boundary.EnumDTO;

@RestController
public class BoardQueryController {

	private BoardQueryService boardQueryService;
	
	public BoardQueryController(BoardQueryService boardQueryService) {
		this.boardQueryService = boardQueryService;
	}
	
	@GetMapping("/api/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<>();
		
		for (BoardType boardType : BoardType.values()) {			
			list.add(new EnumDTO(boardType.toString(), boardType.getName()));
		}				 					
								
		return WebControllerUtil.getResponse(list				
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/api/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return WebControllerUtil.getResponse(list						
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}

	@GetMapping("/api/grw/board")
	public ResponseEntity<?> getBoardList(BoardDTO.SearchBoard dto) {						
		
		List<Board> list = boardQueryService.getBoardList(dto); 										
		List<BoardDTO.FormBoard> dtoList = list.stream()
											   .map(e -> BoardDTO.FormBoard.convertDTO(e))
											   .collect(Collectors.toList());
				
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
