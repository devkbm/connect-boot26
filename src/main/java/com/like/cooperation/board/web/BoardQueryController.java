package com.like.cooperation.board.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardType;
import com.like.cooperation.board.service.BoardQueryService;
import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardQueryController {

	private BoardQueryService boardQueryService;
	
	public BoardQueryController(BoardQueryService boardQueryService) {
		this.boardQueryService = boardQueryService;
	}
	
	@GetMapping("/api/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionRecord.fromEnum(BoardType.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}

	@GetMapping("/api/grw/board")
	public ResponseEntity<?> getBoardList(BoardDTO.Search dto) {						
		
		List<Board> list = boardQueryService.getBoardList(dto); 										
		List<BoardDTO.FormBoard> dtoList = list.stream()
											   .map(e -> BoardDTO.FormBoard.convertDTO(e))
											   .collect(Collectors.toList());
				
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
