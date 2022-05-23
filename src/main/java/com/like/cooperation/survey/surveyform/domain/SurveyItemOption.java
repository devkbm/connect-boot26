package com.like.cooperation.survey.surveyform.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SurveyItemOption {
		
	@Column(name="LABEL")
	String label;
	
	@Column(name="VALUE")
	String value;
	
	@Column(name="SELECTED_YN")
	Boolean isSelected;
		
}
