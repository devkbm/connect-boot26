package com.like.system.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.ResponseMenuHierarchy;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.infra.jparepository.MenuQueryJpaRepository;

@Service
@Transactional(readOnly=true)
public class MenuQueryService {

	private MenuQueryJpaRepository menuJpaRepository;

	public MenuQueryService(MenuQueryJpaRepository menuJpaRepository) {
		this.menuJpaRepository = menuJpaRepository;
	}
		
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition) {
		return menuJpaRepository.getMenuGroupList(condition);
	}
	
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return menuJpaRepository.getMenuGroupList(likeMenuGroupName);
	}				
	
	public List<Menu> getMenuList(MenuDTO.SearchMenu condition) {
		return menuJpaRepository.getMenuList(condition);
	}
	
	public List<ResponseMenuHierarchy> getMenuHierachy(String menuGroupCode) {
		List<ResponseMenuHierarchy> rootList = menuJpaRepository.getMenuRootList(menuGroupCode);
		
		return menuJpaRepository.getMenuHierarchyDTO(rootList);
	}	
		
}
