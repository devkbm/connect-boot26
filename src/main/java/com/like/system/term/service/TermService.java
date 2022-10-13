package com.like.system.term.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.DataDomainDictionaryRepository;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.TermDictionaryRepository;
import com.like.system.term.domain.WordDictionary;
import com.like.system.term.domain.WordDictionaryRepository;

@Service
@Transactional
public class TermService {
	    
	private TermDictionaryRepository repository; 
	private WordDictionaryRepository wordDictionaryRepository;
	private DataDomainDictionaryRepository dataDomainRepository;	
	
    public TermService(TermDictionaryRepository repository,
    				   WordDictionaryRepository wordDictionaryRepository,
    				   DataDomainDictionaryRepository dataDomainRepository) {
    	this.repository = repository;
    	this.wordDictionaryRepository = wordDictionaryRepository;
    	this.dataDomainRepository = dataDomainRepository;
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
			entity = this.createEntity(dto);									
		} else {						  				
			dto.modifyEntity(entity, this.getDataDomainDictionary(dto));
		}
		
		repository.save(entity);
	}	
	
	public void delete(String termId) {
		repository.deleteById(termId);		
	}
	
	private TermDictionary createEntity(TermDTO.FormTerm dto) {
		TermDictionary entity = null;
		DataDomainDictionary dataDomain = this.getDataDomainDictionary(dto);
				
		if (dto.term().size() == 1) {				
			entity = dto.newEntity(this.getWordDictionary(dto.term().get(0)), dataDomain);
		} else if (dto.term().size() > 1) {				
			entity = dto.newEntity(this.getWordDictionary(dto.term()), dataDomain);
		}
		
		return entity;
	}
	
	private DataDomainDictionary getDataDomainDictionary(TermDTO.FormTerm dto) {
		if (dto.dataDomainId() == null) return null;
		
		return dataDomainRepository.findById(dto.dataDomainId()).orElse(null);
	}
	
	private WordDictionary getWordDictionary(String id) {
		return wordDictionaryRepository.findById(id).orElse(null);
	}
	
	private List<WordDictionary> getWordDictionary(List<String> ids) {
		// List<String> 순번대로 조회되지 않아서 수정
		// return wordDictionaryRepository.findAllById(ids);
		
		List<WordDictionary> list = new ArrayList<>(ids.size());
		
		for (String id : ids) {
			list.add(this.getWordDictionary(id));
		}
		return list;
	}
		
}
