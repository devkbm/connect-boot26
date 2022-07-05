package com.like.hrm.hrmtypecode.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import com.like.hrm.hrmtypecode.domain.HrmType;
import com.like.hrm.hrmtypecode.domain.AppointmentTypeEnum;
import com.like.hrm.hrmtypecode.domain.QHrmType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HrmTypeDTO {

	@Data
	public static class SearchHrmType implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmType qType = QHrmType.hrmType;
						
		private String code;
		
		private String codeName;
		
		private String appointmentType;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqHrmType(this.appointmentType))
				.and(likeCodeName(this.codeName));
						
			return builder;
		}
		
		private BooleanExpression eqHrmType(String appointmentType) {
			return hasText(appointmentType) ? qType.appointmentType.eq(AppointmentTypeEnum.valueOf(appointmentType)) : null;					
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			return hasText(codeName) ? qType.name.like("%" + codeName + "%") : null;					
		}
				
	}
	
	@Data	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FormHrmType implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
											
		private String code;			
			
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String appointmentType;
		
		private String comment;
				
		public HrmType newHrmType() {
			return new HrmType(this.code 
					   		  ,this.codeName
					   		  ,this.useYn
					   		  ,this.sequence
					   		  ,appointmentType == null ? null : AppointmentTypeEnum.valueOf(this.appointmentType)
					   		  ,this.comment);
		}
		
		public HrmType changeInfo(HrmType entity) {
			entity.modify(this.codeName
						 ,this.useYn
						 ,this.sequence
						 ,appointmentType == null ? null : AppointmentTypeEnum.valueOf(this.appointmentType)
						 ,this.comment);
			return entity;
		}
		
		public static FormHrmType convert(HrmType entity) {
						
			return new FormHrmType(entity.getId()
					           ,entity.getName()					           
							   ,entity.isUseYn()
							   ,entity.getSequence()
							   ,entity.getAppointmentType() == null ? null : entity.getAppointmentType().toString()
							   ,entity.getComment());
			
		}
		
		
	}
}
