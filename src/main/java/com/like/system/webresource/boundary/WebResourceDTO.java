package com.like.system.webresource.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.like.system.webresource.domain.QWebResource;
import com.like.system.webresource.domain.WebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class WebResourceDTO {
	
	public record SearchWebResource(
			String resourceCode,
			String resourceName,
			String resourceType,
			String url,
			String description
			) {
		
		private static final QWebResource qType = QWebResource.webResource;
		
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
			return hasText(resourceCode) ? qType.resourceCode.like("%"+resourceCode+"%") : null;					
		}
		
		private BooleanExpression likeResourceName(String resourceName) {
			return hasText(resourceName) ? qType.resourceName.like("%"+resourceName+"%") : null;					
		}
		
		private BooleanExpression likeResourceType(String resourceType) {
			return hasText(resourceName) ? qType.resourceType.like("%"+resourceType+"%") : null;			
		}
		
		private BooleanExpression likeUrl(String url) {
			return hasText(url) ? qType.url.like("%"+url+"%") : null;					
		}
		
		private BooleanExpression likeDescription(String description) {
			return hasText(description) ? qType.description.like("%"+description+"%") : null;
		}
		
	}	
	
	@Builder
	public static record FormWebResource(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			@NotEmpty
			String resourceCode,
			@NotEmpty
			String resourceName,
			String resourceType,
			@NotEmpty
			String url,
			String description
			) {
		
		public static FormWebResource convertDTO(WebResource entity) {
			return FormWebResource.builder()
								  .createdDt(entity.getCreatedDt())	
								  .createdBy(entity.getCreatedBy().getLoggedUser())
								  .modifiedDt(entity.getCreatedDt())
								  .modifiedBy(entity.getModifiedBy().getLoggedUser())
								  .resourceCode(entity.getResourceCode())
								  .resourceName(entity.getResourceName())
								  .resourceType(entity.getResourceType())
								  .url(entity.getUrl())
								  .description(entity.getDescription())
								  .build();
		}
		
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
		
	}
	
	
}
