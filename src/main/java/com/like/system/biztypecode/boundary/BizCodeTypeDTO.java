package com.like.system.biztypecode.boundary;

import static org.springframework.util.StringUtils.hasText;

import javax.validation.constraints.NotBlank;

import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizTypeEnum;
import com.like.system.biztypecode.domain.QBizCodeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;

public class BizCodeTypeDTO {

	public record Search(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String typeId,
			String typeName,			
			String bizType
			) {
		
		private static final QBizCodeType qType = QBizCodeType.bizCodeType;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqOrganizationCode(this.organizationCode))
				.and(eqId(this.typeId))
				.and(likeName(this.typeName))				
				.and(eqBizType(this.bizType))
				;
			
			return builder;
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.id.organizationCode.eq(organizationCode);
		}
		
		private BooleanExpression eqId(String id) {
			return hasText(id) ? qType.id.typeId.eq(id) : null;				
		}
		
		private BooleanExpression likeName(String name) {
			return hasText(name) ? qType.name.like("%" + name + "%") : null;			
		}		
		
		private BooleanExpression eqBizType(String bizType) {
			return hasText(bizType) ? qType.bizType.eq(BizTypeEnum.valueOf(bizType)) : null;
		}
	}	
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,
			String typeId,			
			String typeName,			
			Integer sequence,
			String bizType,
			String comment
			) {
				
		public static Form convert(BizCodeType entity) {			
			if (entity == null) return null;
			
			return Form.builder()
					   .organizationCode(entity.getId().getOrganizationCode())
					   .typeId(entity.getId().getTypeId())
					   .typeName(entity.getName())					   
					   .sequence(entity.getSequence())
					   .bizType(entity.getBizType() == null ? null : entity.getBizType().toString())
					   .comment(entity.getComment())
					   .build();
						
		}

		public BizCodeType newEntity() {						
			BizCodeType entity = new BizCodeType(organizationCode, typeId, typeName, BizTypeEnum.valueOf(bizType), comment);
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public BizCodeType modify(BizCodeType entity) {			
			entity.modify(typeName
						 ,sequence
						 ,null
						 ,comment);
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
				
	}	
	
	
}
