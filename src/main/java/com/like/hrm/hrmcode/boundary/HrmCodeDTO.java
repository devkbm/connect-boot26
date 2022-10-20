package com.like.hrm.hrmcode.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;
import com.like.hrm.hrmcode.domain.QHrmCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

public class HrmCodeDTO {

	@Data
	public static class Search implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmCode qType = QHrmCode.hrmCode;
				
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
			return qType.id.typeId.eq(typeId);
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			return hasText(codeName) ? qType.codeName.like("%" + codeName + "%") : null;					
		}
				
	}
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String organizationCode,
			String clientAppUrl,
			String typeId,
			String code,
			String codeName,
			boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public HrmCode newEntity() {
			return new HrmCode(new HrmCodeId(typeId, code)										
								  		,this.codeName
								  		,this.useYn
								   		,this.sequence
								   		,this.comment);
		}
			
		public HrmCode modify(HrmCode entity) {
			entity.modify(this.codeName
						 ,this.useYn
						 ,this.sequence
						 ,this.comment);
			return entity;
		}

		public static Form convert(HrmCode entity) {
			if (entity == null) return null;
			
			return Form.builder()
					   .typeId(entity.getId().getTypeId())
					   .code(entity.getId().getCode())
					   .codeName(entity.getCodeName())
					   .useYn(entity.isUseYn())
					   .sequence(entity.getSequence())
					   .comment(entity.getComment())
					   .build();			
		}
	}
	
	
}
