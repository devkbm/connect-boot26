package com.like.hrm.hrmtypecode.boundary;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;


import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCode;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeId;
import com.like.hrm.hrmtypecode.domain.QHrmTypeDetailCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HrmTypeDetailCodeDTO {

	@Data
	public static class SearchHrmTypeDetailCode implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmTypeDetailCode qType = QHrmTypeDetailCode.hrmTypeDetailCode;
				
		@NotEmpty
		private String typeId;
				
		private String code;
				
		private String codeName;				
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqTypeId(this.typeId))
				.and(likeCodeName(this.codeName));
						
			return builder;
		}
		
		private BooleanExpression eqTypeId(String typeId) {			
			
			return qType.id.codeType.eq(typeId);
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			if (!StringUtils.hasText(codeName)) {
				return null;
			}
			
			return qType.codeName.like("%" + codeName + "%");
		}
				
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FormHrmTypeDetailCode implements Serializable {					
				
		private static final long serialVersionUID = -4493967354550706137L;
			
		private String codeType;
		
		private String code;
		
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
		
		public HrmTypeDetailCode newTypeDetailCode() {
			return new HrmTypeDetailCode(new HrmTypeDetailCodeId(codeType, code)										
								  		,this.codeName
								  		,this.useYn
								   		,this.sequence
								   		,this.comment);
		}
			
		public HrmTypeDetailCode changeInfo(HrmTypeDetailCode entity) {
			entity.modify(this.codeName
						 ,this.useYn
						 ,this.sequence
						 ,this.comment);
			return entity;
		}

		public static FormHrmTypeDetailCode convert(HrmTypeDetailCode entity) {
			if (entity == null) return null;
			
			return new FormHrmTypeDetailCode(entity.getId().getCodeType()
							   ,entity.getId().getCode()
					           ,entity.getCodeName()
					           ,entity.isUseYn()
					           ,entity.getSequence()
					           ,entity.getComment());			
		}
	}
}
