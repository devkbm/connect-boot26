package com.like.system.menu.boundary;

import java.util.List;

import com.like.system.menu.domain.MenuType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMenuHierarchy {

	private String menuGroupId;
	
	private String key;
	
	private String title;
	
	private String parentMenuId;
		
	private String menuType;
	
	private Long sequence;
		
	private Long level;
				
	private String url;
					
	private boolean expanded;
	
	private boolean selected;
	
	private boolean isLeaf;
	
	private List<ResponseMenuHierarchy> children;

	@QueryProjection
	public ResponseMenuHierarchy(String menuGroupId, String key, String title, String parentMenuId,
			MenuType menuType, Long sequence, Long level, String url) {		
		this.menuGroupId = menuGroupId;
		this.key = key;
		this.title = title;
		this.parentMenuId = parentMenuId;
		this.menuType = menuType.toString();
		this.sequence = sequence;
		this.level = level;
		this.url = url;		
		this.expanded = false;
		this.selected = false;
	}

	public void setChildren(List<ResponseMenuHierarchy> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
