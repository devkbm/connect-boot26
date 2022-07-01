package com.like.system.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupRepository;
import com.like.system.menu.domain.MenuRepository;

@Service
@Transactional
public class MenuCommandService {

	private MenuGroupRepository menuGroupRepository;
	private MenuRepository menuRepository;	
			
	public MenuCommandService(MenuGroupRepository menuGroupRepository
							 ,MenuRepository menuRepository) {
		this.menuGroupRepository = menuGroupRepository;
		this.menuRepository = menuRepository;		
	}

	public MenuGroup getMenuGroup(String menuGroupId) {
		return menuGroupRepository.findById(menuGroupId).orElse(null);
	}
	
	public void saveMenuGroup(MenuGroup codeGroup) {
		menuGroupRepository.save(codeGroup);	
	}
	
	public void saveMenuGroup(MenuGroupDTO.FormMenuGroup dto) {
		MenuGroup menuGroup = dto.menuGroupId() != null ? menuGroupRepository.findById(dto.menuGroupId()).orElse(null) : null; 			
		
		if (menuGroup == null) {
			menuGroup = dto.newMenuGroup();
		} else {
			dto.modifyMenuGroup(menuGroup);
		}
		
		menuGroupRepository.save(menuGroup);	
	}
	
	public void deleteMenuGroup(String menuGroupId) {
		menuGroupRepository.deleteById(menuGroupId);
	}
	
	public Menu getMenu(String menuId) {
		return menuRepository.findById(menuId).orElse(null);
	}
	
	public void saveMenu(Menu menu) throws Exception {			
		menuRepository.save(menu);		
	}
	
	public void saveMenu(MenuDTO.FormMenu dto) {
		MenuGroup menuGroup = menuGroupRepository.findById(dto.menuGroupId()).orElse(null);
		Menu menu = menuRepository.findById(dto.menuId()).orElse(null);
		Menu parent = dto.parentMenuId() != null ? menuRepository.findById(dto.parentMenuId()).orElse(null) : null; 						
					
		if (menu == null) {
			menu = dto.newMenu(menuGroup);
		} else {
			dto.modifyMenu(menu, parent, menuGroup);
		}		
		
		menuRepository.save(menu);	
	}
	
	public void deleteMenu(String menuId) {		
		menuRepository.deleteById(menuId);
	}		
	
}
