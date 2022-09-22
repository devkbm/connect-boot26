package com.like.system.term.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.system.core.dto.HtmlSelectOptionable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SystemType implements HtmlSelectOptionable {
	HRM("인사관리"),
	GROUPWARE("그룹웨어"),
	SYSTEM("시스템")
	;

	private String name;
	
	private SystemType(final String name) {
		this.name = name;
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	@Override
	public String getValue() {
		return name;
	}
}
