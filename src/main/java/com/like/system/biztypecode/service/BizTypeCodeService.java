package com.like.system.biztypecode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.boundary.BizTypeCodeDTO;
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
}
