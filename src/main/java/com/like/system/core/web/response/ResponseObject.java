package com.like.system.core.web.response;

import lombok.Data;

@Data
public class ResponseObject<T> {
	
	T data;
	
	int total;	
	
	String message;
	
	//Timestamp timestamp;
	
	//String status;
	
	//String error;
	
	//String path;
	
	public ResponseObject(T data, int total, String message) {
		this.data = data;
		this.total = total;		
		this.message = message;
	}
}
