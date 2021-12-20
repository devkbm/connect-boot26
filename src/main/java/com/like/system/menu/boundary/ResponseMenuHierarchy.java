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

	private String menuGroupCode;
	
	private String key;
	
	private String title;
	
	private String parentMenuCode;
		
	private String menuType;
	
	private Long sequence;
		
	private Long level;
				
	private String url;
					
	private boolean expanded;
	
	private boolean selected;
	
	private boolean isLeaf;
	
	private List<ResponseMenuHierarchy> children;

	@QueryProjection
	public ResponseMenuHierarchy(String menuGroupCode, String key, String title, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url, boolean isLeaf) {		
		this.menuGroupCode = menuGroupCode;
		this.key = key;
		this.title = title;
		this.parentMenuCode = parentMenuCode;
		this.menuType = menuType.toString();
		this.sequence = sequence;
		this.level = level;
		this.url = url;
		this.isLeaf = isLeaf;		
		this.expanded = false;
		this.selected = false;
	}

	public void setChildren(List<ResponseMenuHierarchy> children) {
		this.children = children;
	}
	
	
}
