package com.like.system.term.domain;

import java.util.List;

import com.like.system.term.boundary.TermDTO;

public interface TermQueryRepository {

	List<TermDictionary> getTermList(TermDTO.Search condition);
}
