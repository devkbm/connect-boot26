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
	    
	private TermRepository termRepository;      
	
    public TermService(TermRepository termRepository) {
    	this.termRepository = termRepository;
    }
    
	public TermDictionary getTerm(Long pkTerm) {
		return termRepository.getTerm(pkTerm);
	}
	
	public List<TermDictionary> getTermList() {
		return termRepository.getTermList();
	}
	
	public List<TermDictionary> getTermList(TermDTO.SearchTerm condition) {
		return termRepository.getTermList(condition);
	}

	public void saveTerm(TermDictionary term) {
		termRepository.saveTerm(term);
	}
	
	public void saveTerm(TermDTO.SaveTerm dto) {
		TermDictionary entity = dto.getPkTerm() != null ? termRepository.getTerm(dto.getPkTerm()) : null; 
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
		
		termRepository.saveTerm(entity);
	}	
	
	public void deleteTerm(Long pkTerm) {
		termRepository.deleteTerm(pkTerm);		
	}	
		
}
