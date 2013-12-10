package com.scrumretro.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.scrumretro.web.model.UserRegistrationRequest;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ConfirmationPasswordValidator implements ConstraintValidator<ValidateConfirmationPassword, UserRegistrationRequest> {

	@Override
	public void initialize(ValidateConfirmationPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserRegistrationRequest userRequest, ConstraintValidatorContext context) {
		return StringUtils.equals(userRequest.getConfirmPassword(), userRequest.getPassword());
	}
}
