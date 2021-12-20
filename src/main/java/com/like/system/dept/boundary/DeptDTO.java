package com.like.system.dept.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import com.like.system.core.vo.LocalDatePeriod;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeptDTO {	
	
	@Data
	public static class SearchDept implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QDept qDept = QDept.dept;
		
		String deptCode;
				
		String deptName;
					
		Boolean isEnabled;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeDeptCode(this.deptCode))
				.and(likeDeptName(this.deptCode));
														
			return builder;
		}
		
		private BooleanExpression likeDeptCode(String deptCode) {
			if (!StringUtils.hasText(deptCode)) return null;
			
			return qDept.deptCode.like("%"+deptCode+"%");
		}
		
		private BooleanExpression likeDeptName(String deptName) {
			if (!StringUtils.hasText(deptCode)) return null;
			
			return qDept.deptNameKorean.like("%"+deptName+"%");
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class FormDept implements Serializable {
		
		private static final long serialVersionUID = -670038546212531439L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String parentDeptCode;
		
		@NotEmpty(message="부서코드는 필수 입력 사항입니다.")
		String deptCode;		
		
		@NotEmpty(message="부서명(한글)은 필수 입력 사항입니다.")
		String deptNameKorean;		
		
		String deptAbbreviationKorean;
		
		String deptNameEnglish;
		
		String deptAbbreviationEnglish;
							
		LocalDate fromDate;
				
		LocalDate toDate;
		
		Integer seq;
		
		String comment;
		
		public Dept newDept(@Nullable Dept parentDept) {
			if (this.deptCode == null) {
				new IllegalArgumentException("부서코드가 없습니다.");
			}
			
			return Dept.builder(this.deptCode)					   
					   .deptNameKorean(this.deptNameKorean)
					   .deptAbbreviationKorean(this.deptAbbreviationKorean)
					   .deptNameEnglish(this.deptNameEnglish)
					   .deptAbbreviationEnglish(this.deptAbbreviationEnglish)
					   .period(new LocalDatePeriod(this.fromDate, this.toDate))					   
					   .seq(this.seq)
					   .comment(this.comment)
					   .parentDept(parentDept)
					   .build();
		}
		
		public void modifyDept(Dept dept, @Nullable Dept parentDept) {
			dept.modifyEntity(deptNameKorean
							 ,deptAbbreviationKorean
							 ,deptNameEnglish
							 ,deptAbbreviationEnglish
							 ,new LocalDatePeriod(this.fromDate, this.toDate)
							 ,seq
							 ,comment
							 ,parentDept);
		}
				
	}
	
	public static DeptDTO.FormDept convertDTO(Dept entity) {							
												
		Optional<Dept> parent = Optional.ofNullable(entity.getParentDept());
		Optional<LocalDatePeriod> period= Optional.ofNullable(entity.getPeriod());
		
		FormDept dto = FormDept.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.deptCode(entity.getDeptCode())
								.parentDeptCode(parent.map(Dept::getDeptCode).orElse(null))
								.deptNameKorean(entity.getDeptNameKorean())
								.deptAbbreviationKorean(entity.getDeptAbbreviationKorean())
								.deptNameEnglish(entity.getDeptNameEnglish())
								.deptAbbreviationEnglish(entity.getDeptAbbreviationEnglish())
								.fromDate(period.map(LocalDatePeriod::getFrom).orElse(null))
								.toDate(period.map(LocalDatePeriod::getTo).orElse(null))
								.seq(entity.getSeq())
								.comment(entity.getComment())
								.build();		
		return dto;		
	}	
	
}
