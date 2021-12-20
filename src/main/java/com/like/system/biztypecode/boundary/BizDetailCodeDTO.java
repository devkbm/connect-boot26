package com.like.system.biztypecode.boundary;

import java.io.Serializable;

import com.like.system.biztypecode.domain.BizDetailCode;
import com.like.system.biztypecode.domain.BizDetailCodeId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BizDetailCodeDTO {

	
	@Data	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FormBizDetailCode implements Serializable {
						
		private static final long serialVersionUID = 7876453214715116350L;

		private String typeCode;
		
		private String detailCode;
		
		private String codeName;
		
		private Boolean useYn;
		
		private Integer sequence;
					
		private String comment;
		
		public BizDetailCode newEntity() {			
			return new BizDetailCode(new BizDetailCodeId(typeCode, detailCode), codeName, useYn, sequence, comment);
		}
		
		public BizDetailCode modify(BizDetailCode entity) {
			
			entity.modify(codeName
						 ,useYn
						 ,sequence						 
						 ,comment);
			
			return entity;
		}
		
		public static FormBizDetailCode convert(BizDetailCode entity) {
			
			if (entity == null) return null;
			
			return new FormBizDetailCode(entity.getId().getTypeCode()
							  ,entity.getId().getDetailCode()
							  ,entity.getCodeName()
							  ,entity.getUseYn()
							  ,entity.getSequence()							  
							  ,entity.getComment());
		}
	}
}
