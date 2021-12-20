package com.like.system.user.boundary;

import lombok.Data;

@Data
public class PasswordChangeRequestDTO {

	String userId;
	
	String beforePassword;
	
	String afterPassword;	
}
