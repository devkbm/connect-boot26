package com.like.cooperation.survey.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyItem;
import com.like.cooperation.survey.surveyform.domain.SurveyItemOption;
import com.like.cooperation.survey.surveyform.domain.SurveyItemType;
import com.like.cooperation.survey.surveyform.domain.SurveyRepository;
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class SurveyFormRepositoryTest {

	@Autowired
	private SurveyRepository repository;
	

	@DisplayName("설문 생성")
	@Test
	void createSurveyForm() {
		//Given
		SurveyForm form = new SurveyForm("설문제목", new LocalDatePeriod(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31)), "비고");
		
		//When		
		repository.save(form);
		
		//Then
		assertThat(form.getTitle()).isEqualTo("설문제목");
		assertThat(form.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 1));
		assertThat(form.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 12, 31));
		assertThat(form.getComment()).isEqualTo("비고");
		assertThat(form.getIsFinish()).isEqualTo(false);
	}
	
	@DisplayName("설문 항목 등록 - 1")
	@Test
	void createSurveyItem() {
		//Given
		SurveyForm form = new SurveyForm("설문제목", new LocalDatePeriod(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31)), "비고");
		form.addItem(new SurveyItem(form, SurveyItemType.SIMPLETEXT, "항목1", "비고1", true));
		form.addItem(new SurveyItem(form, SurveyItemType.SIMPLETEXT, "항목2", "비고2", false));
				
		//When		
		repository.save(form);
		
		//Then
		assertThat(form.getTitle()).isEqualTo("설문제목");
		assertThat(form.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 1));
		assertThat(form.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 12, 31));
		assertThat(form.getComment()).isEqualTo("비고");
		assertThat(form.getIsFinish()).isEqualTo(false);
		
		var item1 = form.getItems().get(0);
		assertThat(item1.getItemType()).isEqualTo(SurveyItemType.SIMPLETEXT);
		assertThat(item1.getItemTitle()).isEqualTo("항목1");
		assertThat(item1.getComment()).isEqualTo("비고1");
		assertThat(item1.getRequired()).isEqualTo(true);
		
		var item2 = form.getItems().get(1);
		assertThat(item2.getItemType()).isEqualTo(SurveyItemType.SIMPLETEXT);
		assertThat(item2.getItemTitle()).isEqualTo("항목2");
		assertThat(item2.getComment()).isEqualTo("비고2");
		assertThat(item2.getRequired()).isEqualTo(false);
	}
	
	@DisplayName("설문 항목 등록 - 2")
	@Test
	void createSurveyItem2() {
		//Given
		SurveyForm form = new SurveyForm("설문제목", new LocalDatePeriod(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31)), "비고");
		/*
		List<SurveyItemOption> optionList = List.of(new SurveyItemOption("선택1", "val1", false)
				   								   ,new SurveyItemOption("선택2", "val2", false));
				
		SurveyItem item = new SurveyItem(form, SurveyItemType.CHECKBOX, "항목1", "비고1", true, optionList);
		*/
		SurveyItem item = new SurveyItem(form, SurveyItemType.CHECKBOX, "항목1", "비고1", true);
					
		item.getOptionList().add(new SurveyItemOption("선택1", "val1", false));
		item.getOptionList().add(new SurveyItemOption("선택2", "val2", false));
		
		form.addItem(item);			
		
		//When		
		repository.saveAndFlush(form);
		
		//Then
		assertThat(form.getTitle()).isEqualTo("설문제목");
		assertThat(form.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 1));
		assertThat(form.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 12, 31));
		assertThat(form.getComment()).isEqualTo("비고");
		assertThat(form.getIsFinish()).isEqualTo(false);
		
		var item1 = form.getItems().get(0);
		assertThat(item1.getItemType()).isEqualTo(SurveyItemType.CHECKBOX);
		assertThat(item1.getItemTitle()).isEqualTo("항목1");
		assertThat(item1.getComment()).isEqualTo("비고1");
		assertThat(item1.getRequired()).isEqualTo(true);
		
		var option1 = item1.getOptionList().get(0);
		assertThat(option1.getLabel()).isEqualTo("선택1");
		assertThat(option1.getValue()).isEqualTo("val1");
		assertThat(option1.getIsSelected()).isEqualTo(false);
		
		var option2 = item1.getOptionList().get(1);
		assertThat(option2.getLabel()).isEqualTo("선택2");
		assertThat(option2.getValue()).isEqualTo("val2");
		assertThat(option2.getIsSelected()).isEqualTo(false);				
	}
}
