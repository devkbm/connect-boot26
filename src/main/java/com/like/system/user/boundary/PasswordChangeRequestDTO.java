package com.like.system.user.boundary;

public record PasswordChangeRequestDTO(
		String userId,
		String beforePassword,
		String afterPassword
		) {	
}