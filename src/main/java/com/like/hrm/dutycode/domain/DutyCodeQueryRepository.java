package com.like.hrm.dutycode.domain;

import java.util.List;

import com.like.hrm.dutycode.boundary.DutyCodeDTO;

public interface DutyCodeQueryRepository {
	List<DutyCode> getDutyCodeList(DutyCodeDTO.SearchDutyCode condition);
}
