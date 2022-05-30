package com.like.cooperation.survey.surveyform.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper=true, includeFieldNames=true, exclude = {"surveyForm"})
@Getter
@Entity
@Table(name = "GRWSURVEYITEM")
@EntityListeners(AuditingEntityListener.class)
public class SurveyItem extends AuditEntity {	

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM_ID")
	Long itemId;
		
	@Enumerated(EnumType.STRING)
	@Column(name="ITEM_TYPE")
	SurveyItemType itemType;
	
	@Column(name="ITEM_TITLE")
	String itemTitle;	
	
	@Column(name="CMT")
	String comment;
	
	@Column(name="REQUIRED_YN")
	Boolean required;	
		
	@ElementCollection
    @CollectionTable(
        name = "GRWSURVEYITEM_OPTION", 
        joinColumns = @JoinColumn(name = "ITEM_ID")
    )
	@OrderColumn(name = "OPTION_SEQ")
	List<SurveyItemOption> optionList = new ArrayList<>();
		
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name="form_id", nullable=false, updatable=false)
	private SurveyForm surveyForm;
			
	public SurveyItem(SurveyForm surveyForm
					 ,SurveyItemType itemType
					 ,String itemTitle
					 ,String comment
					 ,Boolean required) {
		this.surveyForm = surveyForm;
		this.itemType = itemType;
		this.itemTitle = itemTitle;
		this.comment = comment;
		this.required = required;		
	}
	
	public SurveyItem(SurveyForm surveyForm
					 ,SurveyItemType itemType
					 ,String itemTitle
					 ,String comment
					 ,Boolean required
					 ,List<SurveyItemOption> optionList) {
			
		this.surveyForm = surveyForm;
		this.itemType = itemType;
		this.itemTitle = itemTitle;
		this.comment = comment;
		this.required = required;						
		this.optionList = optionList;
		
		if (!validOption()) throw new IllegalStateException("CHECKBOX 또는 RADIO만 OPTION 등록 가능합니다.");
	}
	
		
	public void modify(SurveyItemType itemType							
					  ,String itemTitle
					  ,String comment
					  ,boolean required) {
		this.itemType = itemType;
		this.itemTitle = itemTitle;
		this.comment = comment;
		this.required = required;		
	}
	
	public void setOptionList(List<SurveyItemOption> optionList) {
		if (!validOption()) throw new IllegalStateException("CHECKBOX 또는 RADIO만 OPTION 등록 가능합니다.");
		
		this.optionList = optionList;		
	}
	
	private boolean validOption() {
		return this.itemType == SurveyItemType.CHECKBOX || this.itemType == SurveyItemType.RADIO ? true : false;  
	}
	
	/*
	public void addOption(SurveyOption option) {
		if (this.options == null) 
			this.options = new ArrayList<>();
		
		this.options.add(option);
	}
	
	public void removeOption(SurveyOption option) {
		this.options.remove(option);
	}
	*/
	
}
