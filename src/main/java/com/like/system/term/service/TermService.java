package com.like.system.term.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.TermRepository;

@Service
@Transactional
public class TermService {
	    
	private TermRepository repository;      
	
    public TermService(TermRepository repository) {
    	this.repository = repository;
    }
    
	public TermDictionary getTerm(Long pkTerm) {
		return repository.findById(pkTerm).orElse(null);
	}
	
	public List<TermDictionary> getTermList() {
		return repository.findAll();
	}	

	public void saveTerm(TermDictionary term) {
		repository.save(term);
	}
	
	public void saveTerm(TermDTO.SaveTerm dto) {
		TermDictionary entity = null;
		
		if (dto.getPkTerm() != null) entity = repository.findById(dto.getPkTerm()).orElse(null); 
									
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
		
		repository.save(entity);
	}	
	
	public void deleteTerm(Long pkTerm) {
		repository.deleteById(pkTerm);		
	}	
		
}
