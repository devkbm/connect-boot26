package com.like.cooperation.survey.surveyform.boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.cooperation.survey.surveyform.domain.QSurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyItem;
import com.like.system.core.vo.LocalDatePeriod;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class SurveyFormDTO {

	public record SearchForm(Long formId
							,String title) {
		
		private static QSurveyForm qSurveyForm = QSurveyForm.surveyForm;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqFormId(this.formId))
				.and(likeTitle(this.title));
											
			return builder;
		}
		
		private BooleanExpression eqFormId(Long formId) {
			if (formId == null || formId < 0L) {
				return null;
			}
			
			return qSurveyForm.formId.eq(formId);
		}
		
		private BooleanExpression likeTitle(String title) {
			if (!StringUtils.hasText(title)) return null;
						
			return qSurveyForm.title.like("%"+title+"%");
		}		
	}	
	
	public record SaveForm(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			Long formId,
			@NotEmpty
			String title,
			LocalDate from,
			LocalDate to,
			String comment
			) {
		
		public SurveyForm newSurveyForm() {
			return new SurveyForm(title, new LocalDatePeriod(from,to), comment);			
		}
		
		public void modifySurveyForm(SurveyForm surveyForm) {
			surveyForm.modifyEntity(this.title()
								   ,this.comment());			
		}
	}
		
	public record SaveItem(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			Long itemId,
			Long formId,
			String itemType,
			String label,
			String value,
			Boolean required,
			Boolean visible,
			String comment
			) {
		
		public SurveyItem newSaveSurveyItem(SurveyForm form) {
			//return new SurveyItem(form, itemType, label, value, required, null);
			return null;
		}
		
		public void modifySaveSurveyItem(SurveyItem surveyItem) {			
			/*
			surveyItem.modifyEntity(itemType
								   ,label
								   ,value
								   ,required
								   ,visible);
			*/
		}
	}
		
}
