package com.like.system.menu.boundary;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class MenuGroupDTO {

	public record Search(
			String menuGroupCode,
			String menuGroupName
			) {
		private static final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(this.menuGroupCode))
				.and(likeMenGroupName(this.menuGroupName));
											
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String menuGroupCode) {
			if (!StringUtils.hasText(menuGroupCode)) return null;
			
			return qMenuGroup.menuGroupCode.like("%"+menuGroupCode+"%");
		}
		
		private BooleanExpression likeMenGroupName(String menuGroupName) {
			if (!StringUtils.hasText(menuGroupName)) return null;
			
			return qMenuGroup.menuGroupName.like("%"+menuGroupName+"%");
		}
	}	
	
	@Builder
	public static record FormMenuGroup(			
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			@NotEmpty
			String menuGroupCode,
			@NotEmpty
			String menuGroupName,
			String description
			) {
		
		public MenuGroup newMenuGroup() {
			return MenuGroup.builder()
						    .menuGroupCode(this.menuGroupCode)
						    .menuGroupName(this.menuGroupName)
						    .description(this.description)
						    .build();	
		}
		
		public void modifyMenuGroup(MenuGroup menuGroup) {
			menuGroup.modifyEntity(this.menuGroupName, this.description);
		}
		
		public static FormMenuGroup convert(MenuGroup entity) {
			if (entity == null) return null;
			
			return FormMenuGroup.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy().getLoggedUser())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy().getLoggedUser())
								.menuGroupCode(entity.getMenuGroupCode())
								.menuGroupName(entity.getMenuGroupName())
								.description(entity.getDescription())
								.build();
		}
	}
		
}
