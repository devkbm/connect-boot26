package com.like.system.core.jpa.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.like.system.user.service.UserService;

@Component
public class UserIdExistsValidator implements ConstraintValidator<UserIdExists, String> {
	
	private UserService userService;
	
	public UserIdExistsValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void initialize(UserIdExists constraintAnnotation) {	
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userService.CheckDuplicationUser(value);
	}

	
}
