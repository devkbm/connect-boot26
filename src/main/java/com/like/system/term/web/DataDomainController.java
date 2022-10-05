package com.like.system.term.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.boundary.DataDomainDTO;
import com.like.system.term.service.DataDomainService;

@RestController
public class DataDomainController {

	private DataDomainService service;
	
	public DataDomainController(DataDomainService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/datadomin")
	public ResponseEntity<?> getList() {
		
		List<DataDomainDTO.FormDataDomain> list = this.service.getAllList()
															  .stream()
															  .map(e -> DataDomainDTO.FormDataDomain.convert(e))
															  .toList();
			
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	
	@GetMapping("/api/system/datadomin/{id}")
	public ResponseEntity<?> get(@PathVariable String id) {
		
		DataDomainDTO.FormDataDomain dto = DataDomainDTO.FormDataDomain.convert(service.get(id));								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}			
		
	@PostMapping("/api/system/datadomin")
	public ResponseEntity<?> save(@Valid @RequestBody DataDomainDTO.FormDataDomain dto) {
														
		service.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
					
	@DeleteMapping("/api/system/datadomin/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
								
		service.delete(id);										
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
}
