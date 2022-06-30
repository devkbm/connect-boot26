package com.like.system.menu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.service.MenuCommandService;

@RestController
public class MenuFormValidController {

	private MenuCommandService menuQueryService;
	
	public MenuFormValidController(MenuCommandService menuQueryService) {
		this.menuQueryService = menuQueryService;		
	}

	@GetMapping("/api/common/menugroup/{menuGroupId}/check")
	public ResponseEntity<?> getMenuGroupValid(@PathVariable String menuGroupId) {							
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupId);
		Boolean isValid = menuGroup == null ? true : false;				
								
		return ResponseEntityUtil.toOne(isValid
									   ,String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0));
	}
	
	@GetMapping("/api/common/menu/{menuId}/check")
	public ResponseEntity<?> getMenuValid(@PathVariable String menuId) {						
		Menu menu = menuQueryService.getMenu(menuId); 		
		Boolean isValid = menu == null ? true : false;			
		
		return ResponseEntityUtil.toOne(isValid
									   ,String.format("%d 건 조회되었습니다.", menu != null ? 1 : 0));
	}
	
}
