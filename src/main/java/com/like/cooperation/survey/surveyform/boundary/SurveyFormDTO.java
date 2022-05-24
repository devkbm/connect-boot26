package com.like.cooperation.survey.surveyform.boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.cooperation.survey.surveyform.domain.QSurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyItem;
import com.like.cooperation.survey.surveyform.domain.SurveyItemOption;
import com.like.cooperation.survey.surveyform.domain.SurveyItemType;
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
			String itemTitle,
			String comment,
			Boolean required,
			List<Option> options
			) {
		
		public record Option(String label
							,String value
							,Boolean isSelected) {}
		
		public SurveyItem newEntity(SurveyForm form) {
						
			List<SurveyItemOption> optionList = this.convert(this.options);			
			
			return new SurveyItem(form, SurveyItemType.valueOf(itemType), itemTitle, comment, required, optionList); 
		}
		
		public void modifyEntity(SurveyItem surveyItem) {
			
			surveyItem.modify(SurveyItemType.valueOf(itemType)
							 ,itemTitle
							 ,comment
							 ,required);			
		}
		
		private List<SurveyItemOption> convert(List<Option> options) {
			List<SurveyItemOption> optionList = new ArrayList<>(options.size());
			
			if (options != null) {
				this.options.forEach(e -> optionList.add(new SurveyItemOption(e.label(), e.value(), e.isSelected())));
			}
			
			return optionList;
		}
		
		
	}
		
}
