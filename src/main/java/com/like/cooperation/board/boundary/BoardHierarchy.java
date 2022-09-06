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
	
	Long boardParentId;
	
	BoardType boardType;
	
	String title;
	
	String boardDescription;			
			
	boolean expanded;
	
	boolean selected;
	
	@JsonProperty(value="isLeaf")
	boolean isLeaf;
	
	boolean active;
	
	List<BoardHierarchy> children = new ArrayList<BoardHierarchy>();
			
	@QueryProjection
	public BoardHierarchy(Long key, Long boardParentId, BoardType boardType, 
						  String title, String boardDescription) {
		super();
		this.key 				= key;
		this.boardParentId 		= boardParentId;
		this.boardType 			= boardType;
		this.title 				= title;
		this.boardDescription 	= boardDescription;					
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
