package com.like.cooperation.board.boundary;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardType;
import com.like.cooperation.board.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class BoardDTO {
	
	public record Search(
			String boardName,
			String boardType
			) {		
		private static final QBoard qBoard = QBoard.board;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeBoardName(this.boardName))
				.and(equalBoardType(this.boardType));
						
			
			return builder;
		}
		
		private BooleanExpression likeBoardName(String boardName) {
			if (!StringUtils.hasText(boardName)) return null;
			
			return qBoard.boardName.like("%"+boardName+"%");
		}
		
		private BooleanExpression equalBoardType(String boardType) {
			if (!StringUtils.hasText(boardType)) return null;
			
			return qBoard.boardType.eq(BoardType.valueOf(boardType));
		}
	}	
	
	@Builder
	public static record FormBoard(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			Long pkBoard,
			Long ppkBoard,
			String boardType,
			@NotEmpty(message="게시판명은 필수 입력사항입니다.")
			String boardName,
			String boardDescription,			
			Boolean useYn,			
			long sequence
			) {
		
		public Board newBoard(Board parentBoard) {			
			return new Board(parentBoard, BoardType.valueOf(this.boardType), this.boardName, this.boardDescription);					
		}	
		
		public void modifyBoard(Board board, Board parentBoard) {
			board.modifyEntity(parentBoard
					          ,BoardType.valueOf(this.boardType)
					          ,this.boardName
					          ,this.boardDescription					          
					          ,this.useYn
					          ,this.sequence);
		}
		
		public static BoardDTO.FormBoard convertDTO(Board entity) {					
			
			if (entity == null)
				return null;
			
			Optional<Board> parent = Optional.ofNullable(entity.getParent());			
			
			return FormBoard.builder()
						    .createdDt(entity.getCreatedDt())
						    .createdBy(entity.getCreatedBy())
						    .modifiedDt(entity.getModifiedDt())
						    .modifiedBy(entity.getModifiedBy())
						    .pkBoard(entity.getPkBoard())	
						    .ppkBoard(parent.map(Board::getPkBoard).orElse(null))
						    .boardType(entity.getBoardType().toString())
						    .boardName(entity.getBoardName())
						    .boardDescription(entity.getDescription())						   						    
						    .useYn(entity.getUseYn())						    
						    .build();	
		}
	}		
	
}
