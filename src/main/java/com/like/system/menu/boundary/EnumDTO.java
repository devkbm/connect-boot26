package com.like.system.menu.boundary;

public class EnumDTO {

	private String code;
	private String name;
	
	public EnumDTO() { }
	
	public EnumDTO(String code, String name) {	
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	
	
}
