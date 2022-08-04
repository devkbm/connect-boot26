package com.like.cooperation.board.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

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
			return hasText(boardName) ? qBoard.boardName.like("%"+boardName+"%") : null;					
		}
		
		private BooleanExpression equalBoardType(String boardType) {
			return hasText(boardType) ? qBoard.boardType.eq(BoardType.valueOf(boardType)) : null;			
		}
	}	
	
	@Builder
	public static record FormBoard(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String appUrl,
			String organizationCode,
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
						    .createdBy(entity.getCreatedBy().getLoggedUser())
						    .modifiedDt(entity.getModifiedDt())
						    .modifiedBy(entity.getModifiedBy().getLoggedUser())
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
