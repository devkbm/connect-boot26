package com.like.cooperation.workschedule.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.like.cooperation.workschedule.domain.QWorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;


public class WorkDTO {	
	
	public record Search(
			String name
			) {
		
		private static final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeGroupName(this.name));			
			
			return builder;
		}
		
		private BooleanExpression likeGroupName(String name) {
			return hasText(name) ? qWorkGroup.name.like("%"+this.name+"%") : null;			
		}
	}
	
	@Builder
	public static record Form(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String appUrl,
			String organizationCode,
			Long workGroupId,
			@NotEmpty
			String workGroupName,
			String color,
			List<String> memberList
			) {
		
		public WorkGroup newWorkGroup() {
			WorkGroup entity = new WorkGroup(this.workGroupName, this.color);
			entity.setAppUrl(appUrl);
			return entity;
		}
		
		public void modifyWorkGroup(WorkGroup workGroup) {
			workGroup.modifyEntity(this.workGroupName, color);
			
			workGroup.setAppUrl(appUrl);
		}
		
		public static WorkDTO.Form convertDTO(WorkGroup entity) {
			WorkDTO.Form dto = Form.builder()
								   .workGroupId(entity.getId())
								   .workGroupName(entity.getName())
								   .color(entity.getColor())
								   .memberList(entity.getMemberList().stream()
									  	 						     .map(r -> r.getUser().getId())
 										 						     .toList())
								 .build();

			return dto;
		}
	}
	
	
	
	
}
