package com.like.system.hierarchycode.domain;

public enum SystemType {
	COM("코어 모듈"),
	HRM("인적 자원관리 모듈"),
	GRW("그룹웨어 모듈");
	
	private String description;
	
	private SystemType(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
