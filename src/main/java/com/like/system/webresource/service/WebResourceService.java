package com.like.system.webresource.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.webresource.boundary.WebResourceDTO;
import com.like.system.webresource.domain.WebResource;
import com.like.system.webresource.domain.WebResourceRepository;

@Service
@Transactional
public class WebResourceService {

	private WebResourceRepository repository;
	
	public WebResourceService(WebResourceRepository repository) {
		this.repository = repository;		
	}
	
	public WebResource getResource(String resourceId) {
		return repository.findById(resourceId).orElse(null);
	}
	
	public void saveWebResource(WebResource resource) {				
		repository.save(resource);
	}
	
	public void saveWebResource(WebResourceDTO.FormWebResource dto) {	
		WebResource resource = repository.findById(dto.resourceId()).orElse(null);
		
		if (resource == null) {
			resource = dto.newEntity();
		} else {
			dto.modifyEntity(resource);
		}
		
		repository.save(resource);
	}
	
	public void deleteWebResource(String resourceId) {
		repository.deleteById(resourceId);
	}
}
