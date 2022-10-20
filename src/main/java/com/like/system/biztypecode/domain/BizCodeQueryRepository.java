package com.like.system.biztypecode.domain;

import java.util.List;

public interface BizCodeQueryRepository {
	
	List<BizCode> getBizCodeList(String organizactionCode, String typeId);
}
