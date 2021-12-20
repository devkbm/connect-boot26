package com.like.system.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.boundary.WebResourceDTO;
import com.like.system.menu.domain.WebResource;
import com.like.system.menu.domain.WebResourceQueryRepository;

@Service
@Transactional(readOnly=true)
public class WebResourceQueryService {

	private WebResourceQueryRepository repository;
	
	public WebResourceQueryService(WebResourceQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition) {
		return repository.getResourceList(condition);
	}
}
