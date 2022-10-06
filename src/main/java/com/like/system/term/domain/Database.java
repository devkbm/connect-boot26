package com.like.system.term.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.system.core.dto.HtmlSelectOptionable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Database implements HtmlSelectOptionable {
	
	ORACLE("오라클"), 
	MYSQL("MYSQL")
	;
	
	private String name;
	
	private Database(final String name) {
		this.name= name;
	}

	@Override
	public String getLabel() {
		return this.name;
	}

	@Override
	public String getValue() {
		return this.toString();
	}
}
