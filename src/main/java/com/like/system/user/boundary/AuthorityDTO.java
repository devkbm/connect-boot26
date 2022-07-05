package com.like.system.user.boundary;

import static org.springframework.util.StringUtils.hasText;

import javax.validation.constraints.NotEmpty;

import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QAuthority;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class AuthorityDTO {
					
	public record SearchAuthority(
			String authority,
			String description
			) {
		
		private static final QAuthority qType = QAuthority.authority;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeAuthority(this.authority))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression likeAuthority(String authority) {
			return hasText(authority) ? qType.authorityName.like("%"+authority+"%") : null;					
		}
		
		private BooleanExpression likeDescription(String description) {
			return hasText(description) ? qType.description.like("%"+description+"%") : null;					
		}
	}
	
	public record FormAuthority(
			String appUrl,
			@NotEmpty(message="권한은 필수 항목입니다.")
			String authority,
			String description
			) {
		
		public Authority newEntity() {
			Authority entity = new Authority(this.authority, this.description);
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
