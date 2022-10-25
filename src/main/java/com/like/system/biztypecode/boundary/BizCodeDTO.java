package com.like.system.biztypecode.boundary;

import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeType;

import lombok.AccessLevel;
import lombok.Builder;

public class BizCodeDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,
			String typeId,
			String code,
			String codeName,
			Boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public static Form convert(BizCode entity) {			
			if (entity == null) return null;
			
			return Form.builder()
					   .typeId(entity.getId().getBizCodeTypeId().getTypeId())
					   .code(entity.getId().getCode())
					   .codeName(entity.getCodeName())
					   .useYn(entity.getUseYn())
					   .sequence(entity.getSequence())
					   .comment(entity.getComment())
					   .build();			
		}
		
		public BizCode newEntity(BizCodeType bizType) {			
			
			BizCode entity = new BizCode(bizType, code, codeName, useYn, sequence, comment); 
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public BizCode modify(BizCode entity) {
			
			entity.modify(codeName
						 ,useYn
						 ,sequence						 
						 ,comment);
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}		
	}
	
	
}
