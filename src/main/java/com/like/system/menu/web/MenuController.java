package com.like.system.menu.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.service.MenuCommandService;

@RestController
public class MenuController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}
			
	@GetMapping("/api/system/menugroup/{menuGroupId}")
	public ResponseEntity<?> getMenuGroup(@PathVariable String menuGroupId) {				
		
		MenuGroup menuGroup = menuCommandService.getMenuGroup(menuGroupId);
		
		MenuGroupDTO.FormMenuGroup dto = FormMenuGroup.convert(menuGroup);
								
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		
		
	@PostMapping("/api/system/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.FormMenuGroup dto) {							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
		
	@DeleteMapping("/api/system/menugroup/{menuGroupId}")
	public ResponseEntity<?> delCodeGroup(@PathVariable String menuGroupId) {				
												
		menuCommandService.deleteMenuGroup(menuGroupId);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	
	@GetMapping("/api/system/menu/{menuId}")
	public ResponseEntity<?> getMenu(@PathVariable String menuId) {				
		
		Menu menu = menuCommandService.getMenu(menuId); 		
		
		MenuDTO.FormMenu dto = MenuDTO.FormMenu.convert(menu);			
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
	
		
	@PostMapping("/api/system/menu/{menuId}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.FormMenu dto) throws Exception {												
									
		menuCommandService.saveMenu(dto);																			
														 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/system/menu/{menuId}")
	public ResponseEntity<?> delMenu(@PathVariable String menuId) {				
												
		menuCommandService.deleteMenu(menuId);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
