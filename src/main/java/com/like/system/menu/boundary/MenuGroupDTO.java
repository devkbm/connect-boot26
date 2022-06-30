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
			String menuGroupId,
			String menuGroupName
			) {
		private static final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupId(this.menuGroupId))
				.and(likeMenGroupName(this.menuGroupName));
											
			return builder;
		}
		
		private BooleanExpression likeMenGroupId(String menuGroupId) {
			if (!StringUtils.hasText(menuGroupId)) return null;
			
			return qMenuGroup.id.like("%"+menuGroupId+"%");
		}
		
		private BooleanExpression likeMenGroupName(String menuGroupName) {
			if (!StringUtils.hasText(menuGroupName)) return null;
			
			return qMenuGroup.name.like("%"+menuGroupName+"%");
		}
	}	
	
	@Builder
	public static record FormMenuGroup(			
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			@NotEmpty
			String menuGroupId,
			@NotEmpty
			String menuGroupName,
			String description
			) {
		
		public MenuGroup newMenuGroup() {
			return MenuGroup.builder()
						    .id(this.menuGroupId)
						    .name(this.menuGroupName)
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
								.menuGroupId(entity.getId())
								.menuGroupName(entity.getName())
								.description(entity.getDescription())
								.build();
		}
	}
		
}
