package com.like.system.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.like.system.menu.domain.WebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDTO {		
	
	@Data
	public static class SearchMenu implements Serializable {
		
		private static final long serialVersionUID = -7394537330230941998L;

		private final QMenu qMenu = QMenu.menu;
		
		@NotEmpty(message = "필수 입력 값입니다.")
		String menuGroupCode;
		
		String menuCode;
		
		String menuName;
				
		public BooleanBuilder getBooleanBuilder() {																
			return new BooleanBuilder()
					.and(equalMenuGroupCode(this.menuGroupCode))
					.and(likeMenuCode(this.menuCode))
					.and(likeMenuName(this.menuName));
		}
		
		private BooleanExpression equalMenuGroupCode(String menuGroupCode) {					
			return QMenuGroup.menuGroup.menuGroupCode.eq(menuGroupCode);
		}
		
		private BooleanExpression likeMenuCode(String menuCode) {
			if (!StringUtils.hasText(menuCode)) return null;
			
			return qMenu.menuCode.like("%"+menuCode+"%");
		}
		
		private BooleanExpression likeMenuName(String menuName) {
			if (!StringUtils.hasText(menuName)) return null;
			
			return qMenu.menuName.like("%"+menuName+"%");
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormMenu implements Serializable {
		
		private static final long serialVersionUID = 2421325619239144951L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		private String menuGroupCode;
				
		@NotEmpty
		private String menuCode;
			
		@NotEmpty
		private String menuName;
			
		private String parentMenuCode;
		
		private String menuType;
			
		private long sequence;
				
		private long level;
		
		private String resource;												
		
		public Menu newMenu(MenuGroup menuGroup, WebResource resource) {
			return Menu.builder()
					   .menuGroup(menuGroup)
					   .menuCode(this.menuCode)
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
					   	   .createdBy(menu.getCreatedBy())
					   	   .modifiedDt(menu.getModifiedDt())
					   	   .modifiedBy(menu.getModifiedBy())
					   	   .menuGroupCode(menu.getMenuGroup().getMenuGroupCode())
					   	   .menuCode(menu.getMenuCode())
					   	   .menuName(menu.getMenuName())
					   	   .menuType(menu.getMenuType().toString())
					   	   .sequence(menu.getSequence())
					   	   .level(menu.getLevel())
					   	   .parentMenuCode(menu.getParent() == null ? null : menu.getParent().getMenuCode())
					   	   .resource(menu.getResource() == null ? null : menu.getResource().getResourceCode())
					   	   .build();
		}
	}
	
}
