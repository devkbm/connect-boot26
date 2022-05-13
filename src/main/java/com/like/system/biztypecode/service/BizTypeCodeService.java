package com.like.system.biztypecode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizDetailCodeDTO;
import com.like.system.biztypecode.boundary.BizTypeCodeDTO;
import com.like.system.biztypecode.domain.BizDetailCode;
import com.like.system.biztypecode.domain.BizDetailCodeId;
import com.like.system.biztypecode.domain.BizTypeCode;
import com.like.system.biztypecode.domain.BizTypeCodeRepository;

@Service
@Transactional
public class BizTypeCodeService {

	private BizTypeCodeRepository repository;
	
	public BizTypeCodeService(BizTypeCodeRepository repository) {
		this.repository = repository;
	}
	
	public BizTypeCode getBizTypeCode(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveBizTypeCode(BizTypeCodeDTO.FormBizTypeCode dto) {
		BizTypeCode entity = this.getBizTypeCode(dto.id());
		
		if (entity == null) {
			entity = dto.newEntity();			
		} else {
			dto.modify(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteBizTypeCode(String id) {
		repository.deleteById(id);
	}
	
	
	public BizDetailCode getBizDetailCode(BizDetailCodeId id) {				
		return repository.findById(id.getTypeCode()).orElse(null)
						 .getBizDetailCode(id);
	}
	
	public void saveBizDetailCode(BizDetailCodeDTO.FormBizDetailCode dto) {		
		BizTypeCode bizType =  this.getBizTypeCode(dto.typeCode());
		BizDetailCode entity = this.getBizDetailCode(new BizDetailCodeId(dto.typeCode(), dto.detailCode()));
		
		if (entity == null) {			
			entity = dto.newEntity(this.getBizTypeCode(dto.typeCode()));
		} else {
			dto.modify(entity);
		}
		
		repository.save(bizType);
	}
	
	public void deleteBizDetailCode(BizDetailCodeId id) {
		BizTypeCode entity = this.getBizTypeCode(id.getTypeCode());
		entity.remove(this.getBizDetailCode(id));			
	}
	
}
