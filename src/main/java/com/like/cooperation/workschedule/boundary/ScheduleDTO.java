package com.like.cooperation.workschedule.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.cooperation.workschedule.domain.QSchedule;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import lombok.Builder;

public class ScheduleDTO {		
	
	public record Search(
			@NotEmpty
			String fkWorkGroup,
			@NotEmpty
			String fromDate,
			@NotEmpty
			String toDate,
			String title
			) {
		
		private static final QSchedule qSchedule = QSchedule.schedule;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
																									
			OffsetDateTime fromDateTime = getFromDate(this.fromDate);			
			OffsetDateTime toDateTime = getToDate(this.toDate);			
			
			DateTimeExpression<OffsetDateTime> fromExpression = Expressions.asDateTime(fromDateTime);
			DateTimeExpression<OffsetDateTime> toExpression = Expressions.asDateTime(toDateTime);
			
			//DateTimeExpression<LocalDateTime> monthEndDay = Expressions.asDateTime(param.with(TemporalAdjusters.lastDayOfMonth()));					
			// LocalDateTime firstDay = param.with(TemporalAdjusters.firstDayOfMonth());																					
			
			builder.and(fromExpression.between(qSchedule.start, qSchedule.end)
						.or(toExpression.between(qSchedule.start, qSchedule.end))
						.or(qSchedule.start.between(fromExpression, toExpression))
						.or(qSchedule.end.between(fromExpression, toExpression)));
				
			builder.and(inWorkgroupIds(this.changeIdType(this.fkWorkGroup)))
			       .and(likeTitle(this.title));
											
			return builder;
		}					
		
		private BooleanExpression inWorkgroupIds(List<Long> ids) {
			if ( CollectionUtils.isEmpty(ids) ) {
				return null;
			}
			
			return qSchedule.workGroup.id.in(ids);
		}
		
		private BooleanExpression likeTitle(String title) {
			return hasText(title) ? qSchedule.title.like("%"+title+"%") : null;					
		}
		
		/**
		 * 콤마로 구분된 id 매개변수를 List<Long>타입으로 변환한다. 
		 * @param params			ex) 1,2,3
		 * @return List<Long>
		 */
		private List<Long> changeIdType(String params) {
			
			String idArray[] = params.split(","); 			
		
			List<Long> ids = new ArrayList<Long>(idArray.length);
			
			for (int i=0; i<idArray.length; i++) {
				ids.add(Long.parseLong(idArray[i]));
			}	
			
			return ids;
		}
		
		private OffsetDateTime getFromDate(String fromDate) {
			return OffsetDateTime.of(
					Integer.parseInt(fromDate.substring(0, 4)), 
					Integer.parseInt(fromDate.substring(4, 6)), 
					Integer.parseInt(fromDate.substring(6, 8)), 
					0, 
					0, 
					0,
					0, 
					ZoneOffset.ofHours(9));
		}
		
		private OffsetDateTime getToDate(String toDate) {
			return OffsetDateTime.of(
					Integer.parseInt(toDate.substring(0, 4)), 
					Integer.parseInt(toDate.substring(4, 6)), 
					Integer.parseInt(toDate.substring(6, 8)), 
					23, 
					59, 
					59,
					59, 
					ZoneOffset.ofHours(9));		
		}
	}
	
	@Builder
	public record Form(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String clientAppUrl,		
			String organizationCode,			
			Long id,					
			@NotEmpty
			String text,			
			
			//@DateTimeFormat(timezone=)
			OffsetDateTime start,			
			OffsetDateTime end,			
			Boolean allDay,			
			@NotNull
			Long workGroupId
			) {
		
		public Schedule newSchedule(WorkGroup workGroup) {
			
			Schedule entity = Schedule.builder()
									  .title(this.text)
									  .start(this.start)
									  .end(this.end)
									  .allDay(this.allDay)
									  .workGroup(workGroup)
									  .build();
			entity.setAppUrl(clientAppUrl);
			return entity;
		}
		
		public void modifySchedule(Schedule entity) {
			entity.modifyEntity(text, start, end, allDay);
			entity.setAppUrl(clientAppUrl);
		}
		
		public static Form convertDTO(Schedule entity) {
			Form dto = Form.builder()
						   .id(entity.getId())
						   .text(entity.getTitle())
						   .start(entity.getStart())
						   .end(entity.getEnd())
						   .allDay(entity.getAllDay())
						   .workGroupId(entity.getWorkGroup().getId())
						   .build();
															
			return dto;
		}
	}
		
	
	@Builder
	public static record Response(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			Long workGroupId,
			Long id,
			String text,
			String color,
			//@DateTimeFormat(pattern="E MMM dd yyyy HH:mm:ss 'GMT'Z")
			@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
			OffsetDateTime start,
			@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
			OffsetDateTime end,
			Boolean allDay
			) {
		
		public static Response convert(Schedule entity) {
			
			WorkGroup workGroup = entity.getWorkGroup();
			
			Response dto = Response.builder()
								   .workGroupId(workGroup.getId())
								   .id(entity.getId())
								   .text(entity.getTitle())
								   .color(workGroup.getColor())
								   .start(entity.getStart())
								   .end(entity.getEnd())
								   .allDay(entity.getAllDay())																							
								   .build();
																	
			return dto;
		}
	}
		
}
