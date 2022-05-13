package com.like.system.biztypecode.boundary;

import com.like.system.biztypecode.domain.BizDetailCode;
import com.like.system.biztypecode.domain.BizTypeCode;

public class BizDetailCodeDTO {

	public record FormBizDetailCode(
			String typeCode,
			String detailCode,
			String codeName,
			Boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public static FormBizDetailCode convert(BizDetailCode entity) {
			
			if (entity == null) return null;
			
			return new FormBizDetailCode(entity.getId().getTypeCode()
							  ,entity.getId().getDetailCode()
							  ,entity.getCodeName()
							  ,entity.getUseYn()
							  ,entity.getSequence()							  
							  ,entity.getComment());
		}
		
		public BizDetailCode newEntity(BizTypeCode bizType) {			
			return new BizDetailCode(bizType, detailCode, codeName, useYn, sequence, comment);		
		}
		
		public BizDetailCode modify(BizDetailCode entity) {
			
			entity.modify(codeName
						 ,useYn
						 ,sequence						 
						 ,comment);
			
			return entity;
		}		
	}
	
	
}
