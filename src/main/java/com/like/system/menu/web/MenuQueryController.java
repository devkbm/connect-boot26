package com.like.system.menu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.menu.boundary.EnumDTO;
import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.ResponseMenuHierarchy;
import com.like.system.menu.boundary.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;
import com.like.system.menu.service.MenuQueryService;

@RestController
public class MenuQueryController {

	private MenuQueryService menuQueryService;
	
	public MenuQueryController(MenuQueryService menuQueryService) {
		this.menuQueryService = menuQueryService;		
	}
	
	@GetMapping("/api/common/menutest/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachyTest(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup											
											,String.format("%d 건 조회되었습니다.", menuGroup.size()));
	}
	
	@GetMapping("/api/common/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 										
		
		return WebControllerUtil.getResponse(menuGroup											
											,String.format("%d 건 조회되었습니다.", menuGroup.size()));
	}
	
	@GetMapping("/api/common/menugroup")
	public ResponseEntity<?> getMenuGroupList(MenuGroupDTO.Search dto) {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(dto); 							
		
		List<MenuGroupDTO.FormMenuGroup> dtoList = list.stream().map(e -> FormMenuGroup.convert(e)).collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
	
	@GetMapping("/api/common/menu")
	public ResponseEntity<?> getMenuList(@Valid MenuDTO.SearchMenu dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(dto);			
		
		List<MenuDTO.FormMenu> dtoList = list.stream().map(e -> MenuDTO.FormMenu.convert(e)).collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
	
	@GetMapping("/api/common/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<EnumDTO>();
		
		for (MenuType menuType : MenuType.values()) {			
			list.add(new EnumDTO(menuType.toString(), menuType.getName()));
		}
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
}
