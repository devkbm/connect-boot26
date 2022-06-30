package com.like.system.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupRepository;
import com.like.system.menu.domain.MenuRepository;
import com.like.system.webresource.domain.WebResource;
import com.like.system.webresource.domain.WebResourceRepository;

@Service
@Transactional
public class MenuCommandService {

	private MenuGroupRepository menuGroupRepository;
	private MenuRepository menuRepository;
	private WebResourceRepository webResourceRepository;
			
	public MenuCommandService(MenuGroupRepository menuGroupRepository
							 ,MenuRepository menuRepository
							 ,WebResourceRepository webResourceRepository) {
		this.menuGroupRepository = menuGroupRepository;
		this.menuRepository = menuRepository;
		this.webResourceRepository = webResourceRepository;
	}

	public MenuGroup getMenuGroup(String menuGroupCode) {
		return menuGroupRepository.findById(menuGroupCode).orElse(null);
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
	
	public void deleteMenuGroup(String menuGroupCode) {
		menuGroupRepository.deleteById(menuGroupCode);
	}
	
	public Menu getMenu(String menuCode) {
		return menuRepository.findById(menuCode).orElse(null);
	}
	
	public void saveMenu(Menu menu) throws Exception {			
		menuRepository.save(menu);		
	}
	
	public void saveMenu(MenuDTO.FormMenu dto) {
		MenuGroup menuGroup = menuGroupRepository.findById(dto.menuGroupId()).orElse(null);
		Menu menu = menuRepository.findById(dto.menuId()).orElse(null);
		Menu parent = dto.parentMenuId() != null ? menuRepository.findById(dto.parentMenuId()).orElse(null) : null; 
		WebResource resource = dto.resource() != null ? webResourceRepository.findById(dto.resource()).orElse(null) : null;					
					
		if (menu == null) {
			menu = dto.newMenu(menuGroup, resource);
		} else {
			dto.modifyMenu(menu, parent, menuGroup, resource);
		}		
		
		menuRepository.save(menu);	
	}
	
	public void deleteMenu(String menuCode) {
		
		menuRepository.deleteById(menuCode);
	}		
	
}
