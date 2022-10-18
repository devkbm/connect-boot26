package com.like.hrm.hrmtypecode.service;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.domain.HrmType;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCode;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeId;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCodeRepository;
import com.like.hrm.hrmtypecode.domain.HrmTypeRepository;

@Service
@Transactional
public class HrmTypeService {

	private HrmTypeRepository hrmTypeRepository;
	private HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository;	
	
	public HrmTypeService(HrmTypeRepository hrmTypeRepository
						 ,HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository) {		
		this.hrmTypeRepository = hrmTypeRepository;
		this.hrmTypeDetailCodeRepository = hrmTypeDetailCodeRepository;
	}					
	
	public HrmType getHrmType(String id) {
		return hrmTypeRepository.findById(id).orElse(null);
	}
	
	public HrmTypeDTO.Form getHrmTypeDTO(String code) {
		HrmType entity = this.getHrmType(code);
		
		return HrmTypeDTO.Form.convert(entity);
	}
	
	public void saveHrmType(HrmTypeDTO.Form dto) {
		HrmType hrmType = dto.typeId() == null ? null : hrmTypeRepository.findById(dto.typeId()).orElse(null);			
		
		if (hrmType == null) {
			hrmType = dto.newHrmType();
		} else {					
			hrmType = dto.changeInfo(hrmType);
		}
		
		hrmTypeRepository.save(hrmType);		
	}	

	public void deleteHrmType(String id) {
		hrmTypeRepository.deleteById(id);				
	}
	
	public HrmTypeDetailCode getTypeDetailCode(HrmTypeDetailCodeId id) {
		return hrmTypeDetailCodeRepository.findById(id).orElse(null);
	}
	
	public HrmTypeDetailCodeDTO.Form getTypeDetailCodeDTO(HrmTypeDetailCodeId id) {
		
		HrmTypeDetailCode entity = hrmTypeDetailCodeRepository.findById(id).orElse(null);				
		
		return HrmTypeDetailCodeDTO.Form.convert(entity);
	}
	
	public void saveTypeDetailCode(HrmTypeDetailCodeDTO.Form dto) {		
		HrmTypeDetailCode typeDetailCode = null;
			
		if (hasText(dto.typeId()) && hasText(dto.code())) {
			typeDetailCode = this.getTypeDetailCode(new HrmTypeDetailCodeId(dto.typeId(), dto.code()));
		}
			
		if (typeDetailCode == null) {
			typeDetailCode = dto.newEntity();
		} else {
			typeDetailCode = dto.modify(typeDetailCode);
		}
		
		hrmTypeDetailCodeRepository.save(typeDetailCode);
	}
	
	public void deleteTypeDetailCode(HrmTypeDetailCodeId id) {
		hrmTypeDetailCodeRepository.deleteById(id);
	}		
	
}
