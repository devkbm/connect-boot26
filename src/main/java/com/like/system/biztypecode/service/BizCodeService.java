package com.like.system.biztypecode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizCodeDTO;
import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeId;
import com.like.system.biztypecode.domain.BizCodeRepository;
import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizCodeTypeId;
import com.like.system.biztypecode.domain.BizCodeTypeRepository;

@Service
@Transactional
public class BizCodeService {

	private BizCodeRepository repository;
	private BizCodeTypeRepository bizTypeRepository;
	
	public BizCodeService(BizCodeRepository repository
						 ,BizCodeTypeRepository bizTypeRepository) {
		this.repository = repository;
		this.bizTypeRepository = bizTypeRepository;
	}		
	
	public BizCode getBizCode(String organizationCode, String typeId, String code) {				
		return repository.findById(new BizCodeId(organizationCode, typeId, code)).orElse(null);
	}
	
	public void saveBizCode(BizCodeDTO.Form dto) {	
		BizCodeType bizType = this.bizTypeRepository.findById(new BizCodeTypeId(dto.organizationCode(), dto.typeId()))
													.orElseThrow(() -> new IllegalArgumentException("업무코드분류는 필수 값입니다."));
		BizCode entity = this.getBizCode(dto.organizationCode(), dto.typeId(), dto.code());
					
		if (entity == null) {			
			entity = dto.newEntity(bizType);			
		} else {
			dto.modify(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteBizCode(String organizationCode, String typeId, String code) {
		repository.deleteById(new BizCodeId(organizationCode, typeId, code));		
	}
	
}
