package com.like.system.term.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.TermDictionaryRepository;

@Service
@Transactional
public class TermService {
	    
	private TermDictionaryRepository repository;      
	
    public TermService(TermDictionaryRepository repository) {
    	this.repository = repository;
    }
    
	public TermDictionary get(String termId) {
		return repository.findById(termId).orElse(null);
	}
	
	public List<TermDictionary> getList() {
		return repository.findAll();
	}	

	public void save(TermDictionary term) {
		repository.save(term);
	}
	
	public void save(TermDTO.FormTerm dto) {
		TermDictionary entity = dto.termId() == null ? null : repository.findById(dto.termId()).orElse(null); 
												
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
		
		repository.save(entity);
	}	
	
	public void delete(String termId) {
		repository.deleteById(termId);		
	}	
		
}
