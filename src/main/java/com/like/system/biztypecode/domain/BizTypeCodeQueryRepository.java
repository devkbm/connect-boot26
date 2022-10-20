package com.like.system.biztypecode.domain;

import java.util.List;

import com.like.system.biztypecode.boundary.BizCodeTypeDTO;

public interface BizTypeCodeQueryRepository {

	List<BizCodeType> getBizTypeCode(BizCodeTypeDTO.Search searchCondition);
}
