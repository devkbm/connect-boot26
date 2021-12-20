package com.like.system.hierarchycode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CommonCodeRepository;

@Service
@Transactional
public class CommonCodeCommandService {

	private CommonCodeRepository codeRepository;
					
	public CommonCodeCommandService(CommonCodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}
	
	public Code getCode(String commonCodeId) {
		return codeRepository.findById(commonCodeId).orElse(null);
	}

	public void saveCode(Code code) {		
		codeRepository.save(code);		
	}
	
	public void saveCode(CodeDTO.FormCode dto) {
		Code parentCode = null; 
		Code code = null;
		
		if (dto.getParentId() != null) {
			parentCode = codeRepository.findById(dto.getParentId()).orElse(null);
		}
		
		if (dto.getId() != null) {
			code = codeRepository.findById(dto.getId()).orElse(null);
		}
		
		if (code == null) {
			code = dto.newCode(parentCode);
		} else {
			dto.modifyCode(code);
		}
		
		
		codeRepository.save(code);		
	}

	public void deleteCode(String commonCodeId) {
		codeRepository.deleteById(commonCodeId);		
	}
}
