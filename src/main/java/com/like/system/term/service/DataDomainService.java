package com.like.system.term.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.boundary.DataDomainDTO;
import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.DataDomainDictionaryRepository;

@Service
@Transactional
public class DataDomainService {

	private DataDomainDictionaryRepository repository;      
	
    public DataDomainService(DataDomainDictionaryRepository repository) {
    	this.repository = repository;
    }
    
    public DataDomainDictionary get(String id) {
    	return this.repository.findById(id).orElse(null);
    }
    
    public void save(DataDomainDTO.FormDataDomain dto) {
    	DataDomainDictionary entity = dto.domainId() == null ? null : repository.findById(dto.domainId()).orElse(null); 
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			//dto.modifyEntity(entity);
		}
		
		repository.save(entity);
    }
    
    public void delete(String id) {
    	this.repository.deleteById(id);
    }
}
