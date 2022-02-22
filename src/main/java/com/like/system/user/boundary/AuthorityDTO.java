package com.like.system.user.boundary;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QAuthority;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class AuthorityDTO {
					
	public record SearchAuthority(
			String authority,
			String description
			) {
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeAuthority(this.authority))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression likeAuthority(String authority) {
			if (!StringUtils.hasText(authority)) return null;
			
			final QAuthority qAuthority = QAuthority.authority;
			return qAuthority.authorityName.like("%"+authority+"%");
		}
		
		private BooleanExpression likeDescription(String description) {
			if (!StringUtils.hasText(description)) return null;
			
			final QAuthority qAuthority = QAuthority.authority;
			return qAuthority.description.like("%"+description+"%");
		}
	}
	
	public record FormAuthority(
			@NotEmpty(message="권한은 필수 항목입니다.")
			String authority,
			String description
			) {
		
		public Authority newEntity() {
			return new Authority(this.authority, this.description);
		}
		
		public void modifyEntity(Authority authority) {
			authority.modifyEntity(description);
		}
	}
	
	
}
