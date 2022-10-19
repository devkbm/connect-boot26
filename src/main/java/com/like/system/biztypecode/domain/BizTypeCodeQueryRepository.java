package com.like.system.biztypecode.domain;

import java.util.List;

import com.like.system.biztypecode.boundary.BizTypeCodeDTO;

public interface BizTypeCodeQueryRepository {

	List<BizType> getBizTypeCode(BizTypeCodeDTO.Search searchCondition);
}
