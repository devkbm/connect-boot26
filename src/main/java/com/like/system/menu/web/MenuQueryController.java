package com.like.system.menu.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.message.MessageUtil;
import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.boundary.ResponseMenuHierarchy;
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
	
	@GetMapping("/api/system/menutest/{menuGroupId}")
	public ResponseEntity<?> getMenuGroupHierachyTest(@PathVariable String menuGroupId) {				
		
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupId); 							
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}
	
	@GetMapping("/api/system/menuhierarchy/{menuGroupId}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable String menuGroupId) {				
		
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupId); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}
	
	@GetMapping("/api/system/menugroup")
	public ResponseEntity<?> getMenuGroupList(MenuGroupDTO.Search dto) {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(dto); 							
		
		List<MenuGroupDTO.FormMenuGroup> dtoList = list.stream()
													   .map(e -> FormMenuGroup.convert(e))
													   .toList();													   
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
	
	@GetMapping("/api/system/menu")
	public ResponseEntity<?> getMenuList(@Valid MenuDTO.Search dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(dto);			
		
		List<MenuDTO.FormMenu> dtoList = list.stream()
											 .map(e -> MenuDTO.FormMenu.convert(e))
											 .toList();											 
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
	
	@GetMapping("/api/system/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = new ArrayList<HtmlSelectOptionRecord>();
		
		for (MenuType menuType : MenuType.values()) {			
			list.add(new HtmlSelectOptionRecord(menuType.getLabel(), menuType.toString()));
		}
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
