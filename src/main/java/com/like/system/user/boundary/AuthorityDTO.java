package com.like.system.user.boundary;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QAuthority;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class AuthorityDTO {
					
	public record SearchAuthority(
			String authorityId,
			String organizationCode,
			String authorityCode,
			String description
			) {
		
		private static final QAuthority qType = QAuthority.authority;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeAuthorityId(this.authorityId))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression likeAuthorityId(String authorityId) {
			return hasText(authorityId) ? qType.id.like("%"+authorityId+"%") : null;					
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
