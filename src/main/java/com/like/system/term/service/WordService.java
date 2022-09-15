package com.like.system.term.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.boundary.WordDTO;
import com.like.system.term.domain.WordDictionary;
import com.like.system.term.domain.WordDictionaryRepository;

@Service
@Transactional
public class WordService {

	private WordDictionaryRepository repository;      
	
    public WordService(WordDictionaryRepository repository) {
    	this.repository = repository;
    }
    
    public WordDictionary get(String id) {
    	return this.repository.findById(id).orElse(null);
    }
    
    public void save(WordDTO.FormWord dto) {
    	WordDictionary entity = dto.logicalName() == null ? null : repository.findById(dto.logicalName()).orElse(null); 
		
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
