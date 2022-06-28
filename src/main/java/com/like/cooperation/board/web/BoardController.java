package com.like.cooperation.board.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardBookmark;
import com.like.cooperation.board.service.BoardCommandService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class BoardController {
	
	private BoardCommandService boardCommandService;			
		
	public BoardController(BoardCommandService boardCommandService) {
		this.boardCommandService = boardCommandService;		
	}
			
	@GetMapping("/api/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {				
				
		Board board = boardCommandService.getBoard(id);		
		
		BoardDTO.FormBoard dto = BoardDTO.FormBoard.convertDTO(board);				
							
		return ResponseEntityUtil.toOne(dto											
									   ,String.format("%d 건 조회되었습니다.", board != null ? 1 : 0));
	}	
			
	@PostMapping("/api/grw/board")
	public ResponseEntity<?> saveBoard(@RequestBody @Valid final BoardDTO.FormBoard boardDTO) {												 									
		
		boardCommandService.saveBoard(boardDTO);				
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}	
		
	@DeleteMapping("/api/grw/board/{id}")
	public ResponseEntity<?> delBoard(@PathVariable Long id) {					
												
		boardCommandService.deleteBoard(id);							
		
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}		
	
	@GetMapping("/api/grw/board/bookmark/{userId}")
	public ResponseEntity<?> getBoardList(@PathVariable String userId) {						
		
		List<BoardBookmark> list = boardCommandService.getBookmarkList(userId); 										
							
		return ResponseEntityUtil.toList(list											
										,String.format("%d 건 조회되었습니다.", list.size()));
	}
		
	@PostMapping("/api/grw/board/bookmark")
	public ResponseEntity<?> saveBoardBookmark(@RequestBody @Valid final BoardBookmark entity) {
											
		boardCommandService.saveBookmark(entity);				
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/grw/board/bookmark/{id}")
	public ResponseEntity<?> delBoardBookmark(@PathVariable Long id) {					
												
		boardCommandService.deleteBookmark(id);							
		
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}	
			
}