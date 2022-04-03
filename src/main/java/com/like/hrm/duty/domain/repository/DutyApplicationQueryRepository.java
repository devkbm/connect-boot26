package com.like.hrm.duty.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.dutycode.domain.DutyCode;

public interface DutyApplicationQueryRepository {

	long getDutyApplicationCount(String staffId, List<DutyCode> dutyCodeList, LocalDate fromDate, LocalDate toDate);
	
	List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.SearchDutyApplication condition);
}
