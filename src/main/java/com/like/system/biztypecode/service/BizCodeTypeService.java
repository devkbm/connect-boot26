package com.like.system.biztypecode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizCodeTypeDTO;
import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizCodeTypeId;
import com.like.system.biztypecode.domain.BizCodeTypeRepository;

@Service
@Transactional
public class BizCodeTypeService {

	private BizCodeTypeRepository repository;
	
	public BizCodeTypeService(BizCodeTypeRepository repository) {
		this.repository = repository;
	}
	
	public List<BizCodeType> getBizCodeTypeAllList() {
		return repository.findAll();
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
		
}
