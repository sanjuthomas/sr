package com.scrumretro.web.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.scrumretro.web.model.UserPasswordResetRequest;
import com.scrumretro.web.model.UserRegistrationRequest;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestConfirmationPasswordValidator {
	
	private ConfirmationPasswordValidator confirmationPasswordValidator;
	
	@Before
	public void setUp(){
		confirmationPasswordValidator = new ConfirmationPasswordValidator();
	}
	
	@Test
	public void shouldValidateConfirmationPassword(){
		final UserRegistrationRequest userRequest = new UserRegistrationRequest();
		userRequest.setPassword("password");
		userRequest.setConfirmPassword("password");
		assertTrue(confirmationPasswordValidator.isValid(userRequest, null));
	}
	
	@Test
	public void shouldNotValidateConfirmationPassword(){
		final UserRegistrationRequest userRequest = new UserRegistrationRequest();
		userRequest.setPassword("password");
		userRequest.setConfirmPassword("pa55word");
		assertFalse(confirmationPasswordValidator.isValid(userRequest, null));
	}
	
	@Test
	public void shouldValidateResetPasswordRequest(){
		final UserPasswordResetRequest userPasswordResetRequest = new UserPasswordResetRequest();
		userPasswordResetRequest.setNewconfirmPassword("test123");
		userPasswordResetRequest.setNewPassword("test123");
		assertTrue(confirmationPasswordValidator.isValid(userPasswordResetRequest, null));
	}
	
	@Test
	public void shouldNotValidateResetPasswordRequest(){
		final UserPasswordResetRequest userPasswordResetRequest = new UserPasswordResetRequest();
		userPasswordResetRequest.setNewconfirmPassword("test123");
		userPasswordResetRequest.setNewPassword("test");
		assertFalse(confirmationPasswordValidator.isValid(userPasswordResetRequest, null));
	}

}
