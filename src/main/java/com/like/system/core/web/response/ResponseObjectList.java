package com.like.system.core.web.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseObjectList {

	List<?> data;
	
	int total;	
	
	String message;
	
	public ResponseObjectList(List<?> data, int total, String message) {
		this.data = data;
		this.total = total;	
		this.message = message;
	}
}
