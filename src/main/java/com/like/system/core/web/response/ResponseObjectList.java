package com.like.system.core.web.response;

import java.util.List;

import lombok.Getter;

@Getter
public class ResponseObjectList<T> {

	List<T> data;
	
	int total;	
	
	String message;
	
	public ResponseObjectList(List<T> data, int total, String message) {
		this.data = data;
		this.total = total;	
		this.message = message;
	}
}
