package com.scrumretro.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.scrumretro.web.model.UserRequest;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ConfirmationPasswordValidator implements ConstraintValidator<ValidateConfirmationPassword, UserRequest> {

	@Override
	public void initialize(ValidateConfirmationPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserRequest userRequest, ConstraintValidatorContext context) {
		return StringUtils.equals(userRequest.getConfirmPassword(), userRequest.getPassword());
	}
}
