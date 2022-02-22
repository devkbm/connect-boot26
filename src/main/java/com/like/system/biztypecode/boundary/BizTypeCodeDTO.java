package com.like.system.biztypecode.boundary;

import org.springframework.util.StringUtils;

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
			if (!StringUtils.hasText(id)) return null;
							
			return qType.id.eq(id);		
		}
		
		private BooleanExpression likeName(String name) {
			if (!StringUtils.hasText(name)) return null;
			
			return qType.name.like("%" + name + "%");
		}
		
		private BooleanExpression eqUseYn(Boolean useYn) {
			if (useYn == null) return null;
							
			return qType.useYn.eq(useYn);
		}
		
		private BooleanExpression eqBizType(String bizType) {
			if (!StringUtils.hasText(bizType)) return null;
			QBizTypeCode qType = QBizTypeCode.bizTypeCode;				
			return qType.bizType.eq(BizTypeEnum.valueOf(bizType));
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
