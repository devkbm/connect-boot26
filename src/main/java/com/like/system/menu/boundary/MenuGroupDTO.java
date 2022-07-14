package com.like.system.menu.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class MenuGroupDTO {

	public record Search(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String menuGroupId,
			String menuGroupName
			) {
		private static final QMenuGroup qType = QMenuGroup.menuGroup;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqOrganizationCode(this.organizationCode))
				.and(likeMenGroupId(this.menuGroupId))
				.and(likeMenGroupName(this.menuGroupName));
											
			return builder;		
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.organizationCode.eq(organizationCode);
		}
		
		private BooleanExpression likeMenGroupId(String menuGroupId) {
			return hasText(menuGroupId) ? qType.id.like("%"+menuGroupId+"%") : null;					
		}
		
		private BooleanExpression likeMenGroupName(String menuGroupName) {
			return hasText(menuGroupName) ? qType.name.like("%"+menuGroupName+"%") : null;			
		}
	}	
	
	@Builder
	public static record FormMenuGroup(			
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,			
			String menuGroupId,
			String organizationCode,
			String menuGroupCode,		
			String menuGroupName,
			String description
			) {
		
		public MenuGroup newMenuGroup() {
			return MenuGroup.builder()
						    .organizationCode(this.organizationCode)
						    .code(this.menuGroupCode)
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
								.organizationCode(entity.getOrganizationCode())
								.menuGroupId(entity.getId())
								.menuGroupCode(entity.getCode())
								.menuGroupName(entity.getName())
								.description(entity.getDescription())
								.build();
		}
	}
		
}
