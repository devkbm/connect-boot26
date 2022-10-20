package com.like.hrm.hrmcode.domain;

import java.util.List;

import com.like.hrm.hrmcode.boundary.HrmCodeDTO;
import com.like.hrm.hrmcode.boundary.HrmCodeTypeDTO;

public interface HrmCodeQueryRepository {

	/**
	 * 인사 유형을 조회한다.
	 * @return
	 */
	List<HrmCodeType> getHrmCodeTypeList(HrmCodeTypeDTO.Search condition);
	
	/**
	 * 인사 유형 상세 코드 명단을 조회한다.
	 * @param condition
	 * @return 
	 */
	List<HrmCode> getHrmCodeList(HrmCodeDTO.Search condition);
	
}
