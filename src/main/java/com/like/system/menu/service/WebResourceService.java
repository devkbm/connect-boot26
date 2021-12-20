package com.like.system.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.boundary.WebResourceDTO;
import com.like.system.menu.domain.WebResource;
import com.like.system.menu.domain.WebResourceRepository;

@Service
@Transactional
public class WebResourceService {

	private WebResourceRepository repository;
	
	public WebResourceService(WebResourceRepository repository) {
		this.repository = repository;		
	}
	
	public WebResource getResource(String resourceCode) {
		return repository.findById(resourceCode).orElse(null);
	}
	
	public void saveWebResource(WebResource resource) {				
		repository.save(resource);
	}
	
	public void saveWebResource(WebResourceDTO.FormWebResource dto) {	
		WebResource resource = repository.findById(dto.getResourceCode()).orElse(null);
		
		if (resource == null) {
			resource = dto.newWebResource();
		} else {
			dto.modifyWebResource(resource);
		}
		
		repository.save(resource);
	}
	
	public void deleteWebResource(String resourceCode) {
		repository.deleteById(resourceCode);
	}
}
