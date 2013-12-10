package com.scrumretro.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.scrumretro.web.model.UserRequest;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ConfirmationPasswordValidator implements ConstraintValidator<ValidateConfirmationPassword, UserRequest> {

	@Override
	public void initialize(ValidateConfirmationPassword constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(UserRequest value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}


}
