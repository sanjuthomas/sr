package com.scrumretro.web.validation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

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

}
