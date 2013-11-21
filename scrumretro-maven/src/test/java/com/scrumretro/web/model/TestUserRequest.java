package com.scrumretro.web.model;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 * 
 */
public class TestUserRequest {

	private Validator validator;
	
	private UserRequest userRequest;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		userRequest = new UserRequest();
	}
	
	@Test
	public void shouldValidateAllNotEmpty(){
		final Set<ConstraintViolation<UserRequest>> validtionResults = validator.validate(userRequest);
		assertEquals(5, validtionResults.size());
	}
}
