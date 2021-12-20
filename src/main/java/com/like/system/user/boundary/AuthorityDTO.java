package com.like.system.user.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QAuthority;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;

public class AuthorityDTO {
			
	@Data
	public static class SearchAuthority implements Serializable {			
		
		private static final long serialVersionUID = -3030210553466518025L;

		private final QAuthority qAuthority = QAuthority.authority;
		
		String authority;
			
		String description;
			
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeAuthority(this.authority))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression likeAuthority(String authority) {
			if (!StringUtils.hasText(authority)) return null;
			
			return qAuthority.authorityName.like("%"+authority+"%");
		}
		
		private BooleanExpression likeDescription(String description) {
			if (!StringUtils.hasText(description)) return null;
			
			return qAuthority.description.like("%"+description+"%");
		}
		
	}
	
	@Data
	public static class FormAuthority {

		LocalDateTime createdDt;	
			
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		@NotEmpty(message="권한은 필수 항목입니다.")
		String authority;
				
		String description;
		
		public Authority newEntity() {
			return new Authority(this.authority, this.description);
		}
		
		public void modifyEntity(Authority authority) {
			authority.modifyEntity(description);
		}
	}
}
