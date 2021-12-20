package com.like.system.biztypecode.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.system.biztypecode.domain.BizTypeCode;
import com.like.system.biztypecode.domain.BizTypeEnum;
import com.like.system.biztypecode.domain.QBizTypeCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BizTypeCodeDTO {

	public static class Search implements Serializable {
				
		private static final long serialVersionUID = 3195016606532420119L;

		private final QBizTypeCode qType = QBizTypeCode.bizTypeCode;		
		
		private String id;
	
		private String name;
		
		private Boolean useYn;
		
		private String bizType;
		
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
							
			return qType.bizType.eq(BizTypeEnum.valueOf(bizType));
		}
		
	}
	
	@Data	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FormBizTypeCode implements Serializable {
			
		private static final long serialVersionUID = 1435877481946094507L;

		private String id;
		
		private String name;
		
		private Boolean useYn;
		
		private Integer sequence;
		
		private String bizType;
		
		private String comment;
		
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
		
		public static FormBizTypeCode convert(BizTypeCode entity) {
			
			if (entity == null) return null;
			
			return new FormBizTypeCode(entity.getId()
							  ,entity.getName()
							  ,entity.getUseYn()
							  ,entity.getSequence()
							  ,entity.getBizType() == null ? null : entity.getBizType().toString()
							  ,entity.getComment());
		}
	}
	
}
