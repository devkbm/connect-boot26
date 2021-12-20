package com.like.hrm.hrmtypecode.domain;

import java.util.List;

import com.like.hrm.hrmtypecode.boundary.HrmRelationCodeDTO;
import com.like.hrm.hrmtypecode.boundary.SaveHrmRelationCode;

public interface HrmRelationCodeQueryRepository {

	/**
	 * 연관코드리스트를 조회한다.
	 * @param condition
	 * @return
	 */
	List<SaveHrmRelationCode> getRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition);
}
