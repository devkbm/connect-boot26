package com.like.hrm.anualleave.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.anualleave.boundary.AnualLeaveDTO;
import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.hrm.anualleave.domain.model.AnualLeaveRepository;

@Service
@Transactional
public class AnualLeaveService {
	
	private AnualLeaveRepository repository;
	
	public AnualLeaveService(AnualLeaveRepository repository) {
		this.repository = repository;
	}
	
	public AnualLeave getAnualLeave(Integer yyyy, String empId) {
		return this.repository.findById(new AnualLeaveId(yyyy, empId)).orElse(null);
		
	}
	
	public void saveAnualLeave(AnualLeaveDTO.SaveAnualLeave dto) {
		AnualLeave entity = this.getAnualLeave(dto.getYyyy(), dto.getEmpId());
		
		if (entity == null) {
			entity = dto.newAnualLeave();
		} else {
			
		}
		
		this.repository.save(entity);
	}
	
	public void deleteAnualLeave(Integer yyyy, String empId) {
		AnualLeave entity = this.getAnualLeave(yyyy, empId);
		
		this.repository.delete(entity);
	}
}
