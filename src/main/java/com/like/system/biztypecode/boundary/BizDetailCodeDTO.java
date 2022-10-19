package com.like.system.biztypecode.boundary;

import com.like.system.biztypecode.domain.BizTypeCode;
import com.like.system.biztypecode.domain.BizType;

public class BizDetailCodeDTO {

	public record FormBizDetailCode(
			String typeCode,
			String detailCode,
			String codeName,
			Boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public static FormBizDetailCode convert(BizTypeCode entity) {
			
			if (entity == null) return null;
			
			return new FormBizDetailCode(entity.getId().getTypeCode()
										,entity.getId().getDetailCode()
										,entity.getCodeName()
										,entity.getUseYn()
										,entity.getSequence()							  
										,entity.getComment());
		}
		
		public BizTypeCode newEntity(BizType bizType) {			
			return new BizTypeCode(bizType, detailCode, codeName, useYn, sequence, comment);		
		}
		
		public BizTypeCode modify(BizTypeCode entity) {
			
			entity.modify(codeName
						 ,useYn
						 ,sequence						 
						 ,comment);
			
			return entity;
		}		
	}
	
	
}
