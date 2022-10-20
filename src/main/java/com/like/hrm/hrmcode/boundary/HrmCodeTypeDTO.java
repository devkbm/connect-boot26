package com.like.hrm.hrmcode.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

public class HrmCodeTypeDTO {

	@Data
	public static class Search implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmCodeType qType = QHrmCodeType.hrmCodeType;
						
		private String typeId;
		
		private String typeName;
		
		private String appointmentType;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder		
				.and(likeCodeName(this.typeName));
					
			return builder;
		}			
		
		private BooleanExpression likeCodeName(String typeName) {
			return hasText(typeName) ? qType.name.like("%" + typeName + "%") : null;					
		}
				
	}
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String organizationCode,
			String clientAppUrl,
			String typeId,
			String typeName,
			boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public HrmCodeType newEntity() {
			return new HrmCodeType(this.typeId 
					   		  ,this.typeName
					   		  ,this.useYn
					   		  ,this.sequence					   		  
					   		  ,this.comment);
		}
		
		public HrmCodeType modify(HrmCodeType entity) {
			entity.modify(this.typeName
						 ,this.useYn
						 ,this.sequence						 
						 ,this.comment);
			return entity;
		}
		
		public static Form convert(HrmCodeType entity) {
					
			return Form.builder()
					   .typeId(entity.getId())
					   .typeName(entity.getName())
					   .useYn(entity.isUseYn())
					   .sequence(entity.getSequence())
					   .comment(entity.getComment())
					   .build();						
			
		}
	}
		
}
