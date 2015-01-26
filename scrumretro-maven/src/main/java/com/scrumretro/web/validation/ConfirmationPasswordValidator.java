package com.scrumretro.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.scrumretro.web.model.request.UserPasswordResetRequest;
import com.scrumretro.web.model.request.UserRegistrationRequest;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ConfirmationPasswordValidator implements ConstraintValidator<ValidateConfirmationPassword, Object> {

	@Override
	public void initialize(ValidateConfirmationPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object request, ConstraintValidatorContext context) {
		Boolean isValid = false;
		if(request instanceof UserRegistrationRequest){
			final UserRegistrationRequest userRegistrationRequest = (UserRegistrationRequest)request;
			isValid = StringUtils.equals(userRegistrationRequest.getConfirmPassword(),
					userRegistrationRequest.getPassword());
		}else if(request instanceof UserPasswordResetRequest){
			final UserPasswordResetRequest userPasswordResetRequest = (UserPasswordResetRequest)request;
			isValid = StringUtils.equals(userPasswordResetRequest.getNewconfirmPassword(),
					userPasswordResetRequest.getNewPassword());
		}
		return isValid;
	}
}
