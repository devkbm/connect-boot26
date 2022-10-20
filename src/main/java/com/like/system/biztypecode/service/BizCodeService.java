package com.like.system.biztypecode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizCodeDTO;
import com.like.system.biztypecode.boundary.BizCodeTypeDTO;
import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeId;
import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizCodeTypeId;
import com.like.system.biztypecode.domain.BizCodeTypeRepository;

@Service
@Transactional
public class BizCodeService {

	private BizCodeTypeRepository repository;
	
	public BizCodeService(BizCodeTypeRepository repository) {
		this.repository = repository;
	}
	
	public BizCodeType getBizCodeType(String organizationCode, String typeId) {
		return repository.findById(new BizCodeTypeId(organizationCode, typeId)).orElse(null);
	}
	
	public void saveBizCodeType(BizCodeTypeDTO.Form dto) {
		BizCodeType entity = this.getBizCodeType(dto.organizationCode(), dto.typeId());
		
		if (entity == null) {
			entity = dto.newEntity();			
		} else {
			dto.modify(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteBizCodeType(String organizationCode, String typeId) {
		repository.deleteById(new BizCodeTypeId(organizationCode, typeId));
	}	
	
	public BizCode getBizCode(String organizationCode, String typeId, String code) {				
		return repository.findById(new BizCodeTypeId(organizationCode, typeId)).orElse(null)
						 .getBizeCode(new BizCodeId(organizationCode, typeId, code));
	}
	
	public void saveBizCode(BizCodeDTO.Form dto) {		
		BizCodeType bizType = this.getBizCodeType(dto.organizationCode(), dto.typeId());
		BizCode entity = this.getBizCode(dto.organizationCode(), dto.typeId(), dto.code());
		
		if (bizType == null) throw new IllegalArgumentException("bizType은 필수 값입니다.");
		
		if (entity == null) {			
			entity = dto.newEntity(bizType);
		} else {
			dto.modify(entity);
		}
		
		repository.save(bizType);
	}
	
	public void deleteBizCode(String organizationCode, String typeId, String code) {
		BizCodeType entity = this.getBizCodeType(organizationCode, typeId);
		entity.remove(this.getBizCode(organizationCode, typeId, code));			
	}
	
}
