package com.like.system.menu.boundary;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.like.system.webresource.domain.WebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class MenuDTO {	
	
	public record Search(
			@NotEmpty(message = "필수 입력 값입니다.")
			String menuGroupId,
			String menuId,
			String menuName
			) {
		private static final QMenu qMenu = QMenu.menu;
		
		public BooleanBuilder getBooleanBuilder() {																
			return new BooleanBuilder()
					.and(equalMenuGroupCode(this.menuGroupId))
					.and(likeMenuId(this.menuId))
					.and(likeMenuName(this.menuName));
		}
		
		private BooleanExpression equalMenuGroupCode(String menuGroupId) {					
			return QMenuGroup.menuGroup.id.eq(menuGroupId);
		}
		
		private BooleanExpression likeMenuId(String menuId) {
			if (!StringUtils.hasText(menuId)) return null;
			
			return qMenu.id.like("%"+menuId+"%");
		}
		
		private BooleanExpression likeMenuName(String menuName) {
			if (!StringUtils.hasText(menuName)) return null;
			
			return qMenu.name.like("%"+menuName+"%");
		}
	}	
	
	@Builder
	public static record FormMenu(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			@NotEmpty
			String menuGroupId,
			@NotEmpty
			String menuId,
			@NotEmpty
			String menuName,
			String parentMenuId,
			String menuType,
			long sequence,
			long level,
			String resource
			) {
		
		public Menu newMenu(MenuGroup menuGroup, WebResource resource) {
			return Menu.builder()
					   .menuGroup(menuGroup)
					   .menuCode(this.menuId)
					   .menuName(this.menuName)
					   .menuType(MenuType.valueOf(this.menuType))
					   .sequence(this.sequence)
					   .level(this.level)					   
					   .resource(resource)
					   .build();
		}
		
		public void modifyMenu(Menu menu, Menu parentMenu, MenuGroup menuGroup, WebResource resource) {
			menu.modifyEntity(this.menuName
					         ,MenuType.valueOf(this.menuType)
					         ,this.sequence
					         ,this.level
					         ,parentMenu
					         ,menuGroup
					         ,resource);
			
		}
		
		public static FormMenu convert(Menu menu) {
			
			return FormMenu.builder()
					   	   .createdDt(menu.getCreatedDt())
					   	   .createdBy(menu.getCreatedBy().getLoggedUser())
					   	   .modifiedDt(menu.getModifiedDt())
					   	   .modifiedBy(menu.getModifiedBy().getLoggedUser())
					   	   .menuGroupId(menu.getMenuGroup().getId())
					   	   .menuId(menu.getId())
					   	   .menuName(menu.getName())
					   	   .menuType(menu.getType().toString())
					   	   .sequence(menu.getSequence())
					   	   .level(menu.getLevel())
					   	   .parentMenuId(menu.getParent() == null ? null : menu.getParent().getId())
					   	   .resource(menu.getResource() == null ? null : menu.getResource().getResourceCode())
					   	   .build();
		}
	}	
	
}
