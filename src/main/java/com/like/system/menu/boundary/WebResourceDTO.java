package com.like.system.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.system.menu.domain.QWebResource;
import com.like.system.menu.domain.WebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WebResourceDTO {
	
	@Data
	public static class SearchWebResource implements Serializable {
		
		private static final long serialVersionUID = 698694617356322910L;

		private final QWebResource qWebResource = QWebResource.webResource;
		
		String resourceCode;
		
		String resourceName;
		
		String resourceType;
		
		String url;
		
		String description;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeResourceCode(this.resourceCode))
				.and(likeResourceName(this.resourceName))
				.and(likeResourceType(this.resourceType))
				.and(likeUrl(this.url))
				.and(likeDescription(this.description));									
											
			return builder;
		}
		
		private BooleanExpression likeResourceCode(String resourceCode) {
			if (!StringUtils.hasText(resourceCode)) return null;
			
			return qWebResource.resourceCode.like("%"+resourceCode+"%");
		}
		
		private BooleanExpression likeResourceName(String resourceName) {
			if (!StringUtils.hasText(resourceName)) return null;
			
			return qWebResource.resourceName.like("%"+resourceName+"%");
		}
		
		private BooleanExpression likeResourceType(String resourceType) {
			if (!StringUtils.hasText(resourceType)) return null;
			
			return qWebResource.resourceType.like("%"+resourceType+"%");
		}
		
		private BooleanExpression likeUrl(String url) {
			if (!StringUtils.hasText(url)) return null;
			
			return qWebResource.url.like("%"+url+"%");
		}
		
		private BooleanExpression likeDescription(String description) {
			if (!StringUtils.hasText(description)) return null;
			
			return qWebResource.description.like("%"+description+"%");
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class FormWebResource implements Serializable {
				
		private static final long serialVersionUID = -1400051159309726788L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		String resourceCode;
			
		@NotEmpty
		String resourceName; 
				
		String resourceType;
				
		@NotEmpty
		String url;
			
		String description;		
		
		public WebResource newWebResource() {
			return WebResource.builder()
							  .resourceCode(this.resourceCode)
							  .resourceName(this.resourceName)
							  .resourceType(this.resourceType)
							  .url(this.url)
							  .description(this.description)
							  .build();	
		}
		
		public void modifyWebResource(WebResource entity) {
			entity.modifyEntity(resourceName
							   ,resourceType
							   ,url
							   ,description);
		}
		
		public static FormWebResource convertDTO(WebResource entity) {
			return FormWebResource.builder()
								  .createdDt(entity.getCreatedDt())	
								  .createdBy(entity.getCreatedBy())
								  .modifiedDt(entity.getCreatedDt())
								  .modifiedBy(entity.getModifiedBy())
								  .resourceCode(entity.getResourceCode())
								  .resourceName(entity.getResourceName())
								  .resourceType(entity.getResourceType())
								  .url(entity.getUrl())
								  .description(entity.getDescription())
								  .build();
		}
		
	}
}
