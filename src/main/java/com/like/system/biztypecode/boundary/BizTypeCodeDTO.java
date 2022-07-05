package com.like.system.biztypecode.boundary;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.biztypecode.domain.BizTypeCode;
import com.like.system.biztypecode.domain.BizTypeEnum;
import com.like.system.biztypecode.domain.QBizTypeCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class BizTypeCodeDTO {

	public record Search(
			String id,
			String name,
			Boolean useYn,
			String bizType
			) {
		
		private static final QBizTypeCode qType = QBizTypeCode.bizTypeCode;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqId(this.id))
				.and(likeName(this.name))
				.and(eqUseYn(this.useYn))
				.and(eqBizType(this.bizType))
				;
			
			return builder;
		}
		
		private BooleanExpression eqId(String id) {
			return hasText(id) ? qType.id.eq(id) : null;				
		}
		
		private BooleanExpression likeName(String name) {
			return hasText(name) ? qType.name.like("%" + name + "%") : null;			
		}
		
		private BooleanExpression eqUseYn(Boolean useYn) {
			return useYn != null ? qType.useYn.eq(useYn) : null;					
		}
		
		private BooleanExpression eqBizType(String bizType) {
			return hasText(bizType) ? qType.bizType.eq(BizTypeEnum.valueOf(bizType)) : null;
		}
	}	
	
	public record FormBizTypeCode(
			String id,
			String name,
			Boolean useYn,
			Integer sequence,
			String bizType,
			String comment
			) {
				
		public static FormBizTypeCode convert(BizTypeCode entity) {
			
			if (entity == null) return null;
			
			return new FormBizTypeCode(entity.getId()
									  ,entity.getName()
									  ,entity.getUseYn()
									  ,entity.getSequence()
									  ,entity.getBizType() == null ? null : entity.getBizType().toString()
									  ,entity.getComment());
		}

		public BizTypeCode newEntity() {
			return new BizTypeCode(id, name, useYn, sequence, BizTypeEnum.valueOf(bizType), comment);
		}
		
		public BizTypeCode modify(BizTypeCode entity) {
			
			entity.modify(name
						 ,useYn
						 ,sequence
						 ,null
						 ,comment);
			
			return entity;
		}
				
	}	
	
	
}
