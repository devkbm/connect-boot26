package com.like.system.hierarchycode.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {	
	
	@Data
	public static class SearchCode implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QCode qType = QCode.code1;
		
		String id;
		
		String systemTypeCode;
		
		String parentId;
			
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;					
					
		Boolean isUse = true;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
					
			builder
				.and(eqId(this.id))					// 특정 아이디만 검색	
				.and(eqSystemTypeCode(this.systemTypeCode))
				.and(eqParentId(this.parentId))	 	// 특정 아이디의 하위 코드 검색
				.and(likeCode(this.code))
				.and(likeCodeName(this.codeName))
				.and(likeCodeNameAbbreviation(this.codeNameAbbreviation));
																					
			if (this.isUse) {																						
				builder.and(qType.enabled());											
			} 
			
			return builder;
		}
		
		private BooleanExpression eqId(String id) {
			return hasText(id) ? qType.id.eq(id) : null;					
		}
		
		private BooleanExpression eqSystemTypeCode(String systemTypeCode) {
			return hasText(systemTypeCode) ? qType.systemTypeCode.eq(systemTypeCode) : null;					
		}
		
		private BooleanExpression eqParentId(String parentId) {
			return hasText(parentId) ? qType.parentCode.id.eq(parentId) : null;					
		}
		
		private BooleanExpression likeCode(String code) {
			return hasText(code) ? qType.code.like("%"+code+"%") : null;					
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			return hasText(codeName) ? qType.codeName.like("%"+codeName+"%") : null;					
		}
		
		private BooleanExpression likeCodeNameAbbreviation(String codeNameAbbreviation) {
			return hasText(codeNameAbbreviation) ? qType.codeNameAbbreviation.like("%"+codeNameAbbreviation+"%") : null;
		}
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FormCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String id;
		
		String systemTypeCode;
		
		String parentId;
			
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;		
						
		LocalDateTime fromDate;
			
		LocalDateTime toDate;			
		
		Integer hierarchyLevel;
				
		Integer seq;
			
		boolean fixedLengthYn;
		
		Integer codeLength;
		
		String cmt;		
		
		public Code newCode(Code parentCode) {
						
			return Code.builder()
					   .systemTypeCode(this.systemTypeCode)
					   .parentCode(parentCode)
					   .code(this.code)
					   .codeName(this.codeName)
					   .codeNameAbbreviation(this.codeNameAbbreviation)				
					   .fromDate(this.fromDate)
					   .toDate(this.toDate)
					   .seq(this.seq)
					   .fixedLengthYn(this.fixedLengthYn)
					   .codeLength(this.codeLength)
					   .cmt(this.cmt)
					   .build();
		}
		
		public void modifyCode(Code code) {
			code.modifyEntity(this.codeName
							 ,this.codeNameAbbreviation
							 ,this.fromDate
							 ,this.toDate
							 ,this.seq
							 ,this.fixedLengthYn
							 ,this.codeLength
							 ,this.cmt);
		}
		
		public static CodeDTO.FormCode convertDTO(Code entity) {					
			
			Code parent = entity.getParentCode();
										
			return FormCode.builder()
						   .createdDt(entity.getCreatedDt())
						   .createdBy(entity.getCreatedBy().getLoggedUser())
						   .modifiedDt(entity.getModifiedDt())
						   .modifiedBy(entity.getModifiedBy().getLoggedUser())
						   .id(entity.getId())
						   .systemTypeCode(entity.getSystemTypeCode())
						   .parentId(parent == null ? null : parent.getId())
						   .code(entity.getCode())
						   .codeName(entity.getCodeName())
						   .codeNameAbbreviation(entity.getCodeNameAbbreviation())								
						   .fromDate(entity.getFromDate())
						   .toDate(entity.getToDate())
						   .seq(entity.getSeq())
						   .fixedLengthYn(entity.isFixedLengthYn())
						   .codeLength(entity.getCodeLength())
						   .cmt(entity.getCmt())
						   .build();	
		}
				
	}
			
	
}
