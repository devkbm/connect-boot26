package com.like.cooperation.survey.surveyform.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AuditEntity;
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper=true, includeFieldNames=true)
@Getter
@Entity
@Table(name = "GRWSURVEYFORM")
@EntityListeners(AuditingEntityListener.class)
public class SurveyForm extends AuditEntity {
		
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FORM_ID")
	Long formId;
	
	@Column(name="TITLE")
	String title;
	
	@Embedded
	LocalDatePeriod period;	
	
	@Column(name="FINISH_YN")
	Boolean isFinish;
	
	@Column(name="CMT")
	String comment;
	
	@OneToMany(mappedBy = "surveyForm", cascade = CascadeType.ALL, orphanRemoval = true )
	List<SurveyItem> items = new ArrayList<>();
		
	
	public SurveyForm(String title
			         ,LocalDatePeriod period
			         ,String comment) {
		this.title = title;
		this.period = period;
		this.comment = comment;
		
		this.isFinish = false;
	}
	
	public void modifyEntity(String title
							,String comment) {
		this.title = title;
		this.comment = comment;
	}
	
	public SurveyItem getItem(Long id) {
		return this.items.stream().filter(o -> o.getItemId().equals(id)).findFirst().orElse(null);		
	}
	
	public void addItem(SurveyItem item) {
		if (this.items == null) this.items = new ArrayList<>();
		
		this.items.add(item);
	}
	   	
	public void removeItem(SurveyItem item) {
		this.items.remove(item);
	}
	
	public void removeItem(Long id) {
		this.items.remove(this.getItem(id));
	}
	
	public static void main(String[] args) {
		//SurveyForm form = new SurveyForm(1L, "title", "comment", null);
		
		//form.addItem(new SurveyItem(1L, "itemType", "label1", "value", true, "comment", form));
		//form.addItem(new SurveyItem(2L, "itemType", "label2", "value", true, "comment", form));
		//System.out.println(form.getItem(3L).getLabel());		
		
	}
}
