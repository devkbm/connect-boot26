package com.like.system.biztypecode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizDetailCodeDTO;
import com.like.system.biztypecode.boundary.BizTypeCodeDTO;
import com.like.system.biztypecode.domain.BizTypeCode;
import com.like.system.biztypecode.domain.BizTypeCodeId;
import com.like.system.biztypecode.domain.BizType;
import com.like.system.biztypecode.domain.BizTypeRepository;

@Service
@Transactional
public class BizTypeCodeService {

	private BizTypeRepository repository;
	
	public BizTypeCodeService(BizTypeRepository repository) {
		this.repository = repository;
	}
	
	public BizType getBizTypeCode(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveBizTypeCode(BizTypeCodeDTO.FormBizTypeCode dto) {
		BizType entity = this.getBizTypeCode(dto.id());
		
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
	
	
	public BizTypeCode getBizDetailCode(BizTypeCodeId id) {				
		return repository.findById(id.getTypeCode()).orElse(null)
						 .getBizDetailCode(id);
	}
	
	public void saveBizDetailCode(BizDetailCodeDTO.FormBizDetailCode dto) {		
		BizType bizType =  this.getBizTypeCode(dto.typeCode());
		BizTypeCode entity = this.getBizDetailCode(new BizTypeCodeId(dto.typeCode(), dto.detailCode()));
		
		if (entity == null) {			
			entity = dto.newEntity(this.getBizTypeCode(dto.typeCode()));
		} else {
			dto.modify(entity);
		}
		
		repository.save(bizType);
	}
	
	public void deleteBizDetailCode(BizTypeCodeId id) {
		BizType entity = this.getBizTypeCode(id.getTypeCode());
		entity.remove(this.getBizDetailCode(id));			
	}
	
}
