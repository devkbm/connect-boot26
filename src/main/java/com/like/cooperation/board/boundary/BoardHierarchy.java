package com.like.cooperation.board.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.cooperation.board.domain.BoardType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardHierarchy implements Serializable {
	
	private static final long serialVersionUID = -1211211842381004404L;

	Long key;
	
	Long ppkBoard;
	
	BoardType boardType;
	
	String title;
	
	String boardDescription;		
	
	Long articleCount;
	
	Long sequence;
			
	boolean expanded;
	
	boolean selected;
	
	@JsonProperty(value="isLeaf")
	boolean isLeaf;
	
	boolean active;
	
	List<BoardHierarchy> children = new ArrayList<BoardHierarchy>();
			
	@QueryProjection
	public BoardHierarchy(
			Long key, Long ppkBoard, BoardType boardType, 
			String title, String boardDescription, Long articleCount, Long sequence) {
		super();
		this.key 				= key;
		this.ppkBoard 			= ppkBoard;
		this.boardType 			= boardType;
		this.title 				= title;
		this.boardDescription 	= boardDescription;			
		this.articleCount 		= articleCount;
		this.sequence 			= sequence;
		this.expanded 			= false;
		this.selected 			= false;
		this.active 			= false;
	}
	
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public void setChildren(List<BoardHierarchy> children) {
		this.children = children;	
	}
}
