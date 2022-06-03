package com.like.system.menu.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
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
			
	@GetMapping("/api/common/menugroup/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroup(@PathVariable String menuGroupCode) {				
		
		MenuGroup menuGroup = menuCommandService.getMenuGroup(menuGroupCode);
		
		MenuGroupDTO.FormMenuGroup dto = FormMenuGroup.convert(menuGroup);
								
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0));
	}		
		
	@PostMapping("/api/common/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.FormMenuGroup dto) {							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1));
	}
		
	@DeleteMapping("/api/common/menugroup/{menuGroupCode}")
	public ResponseEntity<?> delCodeGroup(@PathVariable String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(menuGroupCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
	
	@GetMapping("/api/common/menu/{menucode}")
	public ResponseEntity<?> getMenu(@PathVariable String menuCode) {				
		
		Menu menu = menuCommandService.getMenu(menuCode); 		
		
		MenuDTO.FormMenu dto = MenuDTO.FormMenu.convert(menu);			
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0));
	}
	
	
		
	@PostMapping("/api/common/menu/{menucode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.FormMenu dto) throws Exception {												
									
		menuCommandService.saveMenu(dto);																			
														 				
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/common/menu/{menuCode}")
	public ResponseEntity<?> delMenu(@PathVariable String menuCode) {				
												
		menuCommandService.deleteMenu(menuCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
	
	
	
	
	
	
}
