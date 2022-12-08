package com.like.hrm.hrmcode.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.util.Optional;

import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.domain.HrmCodeAddInfoDescription;
import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

public class HrmCodeTypeDTO {

	@Data
	public static class Search implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmCodeType qType = QHrmCodeType.hrmCodeType;
						
		private String typeId;
		
		private String typeName;
		
		private String appointmentType;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder		
				.and(likeCodeName(this.typeName));
					
			return builder;
		}			
		
		private BooleanExpression likeCodeName(String typeName) {
			return hasText(typeName) ? qType.name.like("%" + typeName + "%") : null;					
		}
				
	}
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String organizationCode,
			String clientAppUrl,
			String typeId,
			String typeName,			
			Integer sequence,
			String comment,
			String the1AddInfoDesc,
			String the2AddInfoDesc,
			String the3AddInfoDesc,
			String the4AddInfoDesc,
			String the5AddInfoDesc
			) {
		
		public HrmCodeType newEntity() {
			HrmCodeType entity = new HrmCodeType(this.typeId 
										   		,this.typeName
										   		,this.sequence					   		  
										   		,this.comment
										   		,new HrmCodeAddInfoDescription(
										   				this.the1AddInfoDesc
			   										   ,this.the2AddInfoDesc
			   										   ,this.the3AddInfoDesc
			   										   ,this.the4AddInfoDesc
			   										   ,this.the5AddInfoDesc)); 
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public HrmCodeType modify(HrmCodeType entity) {
			entity.modify(this.typeName						 
						 ,this.sequence						 
						 ,this.comment
						 ,new HrmCodeAddInfoDescription(
								 	   this.the1AddInfoDesc
									  ,this.the2AddInfoDesc
									  ,this.the3AddInfoDesc
									  ,this.the4AddInfoDesc
									  ,this.the5AddInfoDesc));
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public static Form convert(HrmCodeType entity) {
			if (entity == null) return null;		
			Optional<HrmCodeAddInfoDescription> desc = Optional.ofNullable(entity.getAddInfoDesc());
			
			return Form.builder()
					   .typeId(entity.getId())
					   .typeName(entity.getName())					   
					   .sequence(entity.getSequence())
					   .comment(entity.getComment())
					   .the1AddInfoDesc(desc.map(HrmCodeAddInfoDescription::getThe1AddInfoDesc).orElse(null))
					   .the2AddInfoDesc(desc.map(HrmCodeAddInfoDescription::getThe2AddInfoDesc).orElse(null))
					   .the3AddInfoDesc(desc.map(HrmCodeAddInfoDescription::getThe3AddInfoDesc).orElse(null))
					   .the4AddInfoDesc(desc.map(HrmCodeAddInfoDescription::getThe4AddInfoDesc).orElse(null))
					   .the5AddInfoDesc(desc.map(HrmCodeAddInfoDescription::getThe5AddInfoDesc).orElse(null))
					   .build();						
			
		}
	}
		
}
