package com.like.system.biztypecode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class BizCodeQueryService {

	private BizCodeQueryRepository repository;
	
	public BizCodeQueryService(BizCodeQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<BizCode> getBizCodeAllList(String organizationCode, String typeId) {
		return this.repository.getBizCodeList(organizationCode, typeId);
	}
}
