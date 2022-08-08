package com.like.system.user.boundary;

import static org.springframework.util.StringUtils.hasText;

import javax.validation.constraints.NotBlank;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QAuthority;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class AuthorityDTO {
					
	public record SearchAuthority(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String authorityCode,
			String authorityId,
			String description
			) {
		
		private static final QAuthority qType = QAuthority.authority;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(eqOrganizationCode(this.organizationCode))
				   .and(likeAuthorityCode(this.authorityCode))
			       .and(eqAuthorityId(this.authorityId))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.organizationCode.eq(organizationCode);
		}
		
		private BooleanExpression likeAuthorityCode(String authorityCode) {
			return hasText(authorityCode) ? qType.authorityCode.like("%"+authorityCode+"%") : null;					
		}
		
		private BooleanExpression eqAuthorityId(String authorityId) {
			return hasText(authorityId) ? qType.id.eq(authorityId) : null;					
		}
		
		private BooleanExpression likeDescription(String description) {
			return hasText(description) ? qType.description.like("%"+description+"%") : null;					
		}
	}
	
	public record FormAuthority(
			String appUrl,			
			String authorityId,
			String organizationCode,
			String authorityCode,
			String description
			) {
		
		public Authority newEntity() {
			Authority entity = new Authority(this.organizationCode, this.authorityCode, this.description);
			entity.setAppUrl(appUrl);
			System.out.println(entity.toString());
			
			return entity;
		}
		
		public void modifyEntity(Authority authority) {			
			authority.modifyEntity(description);
			authority.setAppUrl(appUrl);
		}
	}
	
	
}
