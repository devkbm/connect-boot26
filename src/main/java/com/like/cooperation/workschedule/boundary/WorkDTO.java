package com.like.cooperation.workschedule.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.like.cooperation.workschedule.domain.QWorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class WorkDTO {	
	
	@Data
	public static class SearchWorkGroup implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
						
		String name;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeGroupName(this.name));			
			
			return builder;
		}
		
		private BooleanExpression likeGroupName(String name) {
			return hasText(name) ? qWorkGroup.name.like("%"+this.name+"%") : null;			
		}
	}	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormWorkGroup implements Serializable {
				
		private static final long serialVersionUID = 8230052719254860669L;

		LocalDateTime createdDt;	 
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		Long workGroupId;
				
		@NotEmpty
		String workGroupName;		
		
		String color;
		
		List<String> memberList;
		
		public WorkGroup newWorkGroup() {
			return new WorkGroup(this.workGroupName, this.color);
		}
		
		public void modifyWorkGroup(WorkGroup workGroup) {
			workGroup.modifyEntity(this.workGroupName, color);
		}
		
		public static WorkDTO.FormWorkGroup convertDTO(WorkGroup entity) {
			WorkDTO.FormWorkGroup dto = FormWorkGroup.builder()
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
