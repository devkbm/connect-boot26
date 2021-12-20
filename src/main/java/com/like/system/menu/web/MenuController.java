package com.like.system.menu.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.exception.ControllerException;
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
			
	@GetMapping("/api/common/menugroup/{id}")
	public ResponseEntity<?> getMenuGroup(@PathVariable(value="id") String menuGroupCode) {				
		
		MenuGroup menuGroup = menuCommandService.getMenuGroup(menuGroupCode);
		
		MenuGroupDTO.FormMenuGroup dto = FormMenuGroup.convert(menuGroup);
								
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
											,HttpStatus.OK);
	}		
		
	@PostMapping("/api/common/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.FormMenuGroup dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/api/common/menugroup/{id}")
	public ResponseEntity<?> delCodeGroup(@PathVariable(value="id") String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(menuGroupCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/api/common/menu/{menucode}")
	public ResponseEntity<?> getMenu(@PathVariable(value="menucode") String menuCode) {				
		
		Menu menu = menuCommandService.getMenu(menuCode); 		
		
		MenuDTO.FormMenu dto = MenuDTO.FormMenu.convert(menu);			
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
											,HttpStatus.OK);
	}
	
	
		
	@PostMapping("/api/common/menu/{menucode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.FormMenu dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors()) {
			//throw new ControllerException("오류");
			//log.info(result.getAllErrors().toString());
		} 					
									
		menuCommandService.saveMenu(dto);																			
														 				
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/common/menu/{id}")
	public ResponseEntity<?> delMenu(@PathVariable(value="id") String menuCode) {				
												
		menuCommandService.deleteMenu(menuCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
