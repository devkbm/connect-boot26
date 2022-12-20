package com.like.system.core.web.response;

import lombok.Getter;

@Getter
public class ResponseObject<T> {
	
	T data;
	
	int total;	
	
	String message;	
	
	public ResponseObject(T data, int total, String message) {
		this.data = data;
		this.total = total;		
		this.message = message;
	}
}
