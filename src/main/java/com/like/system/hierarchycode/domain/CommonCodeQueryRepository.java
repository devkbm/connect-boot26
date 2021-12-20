package com.like.system.hierarchycode.domain;

import java.util.List;

import com.like.system.hierarchycode.boundary.CodeComboDTO;
import com.like.system.hierarchycode.boundary.CodeDTO;
import com.like.system.hierarchycode.boundary.CodeHierarchy;
import com.querydsl.core.types.Predicate;

public interface CommonCodeQueryRepository {

	List<Code> getCodeList(String parentCodeId);
	
	List<Code> getCodeList(Predicate predicate);
	
	List<CodeHierarchy> getCodeHierarchyList(CodeDTO.SearchCode dto);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
}
