package com.scrumretro.web.model;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.databene.benerator.anno.Source;
import org.databene.feed4junit.Feeder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(Feeder.class)
public class TestUserRequest {

	private Validator validator;

	@Before
	public void setUp(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldValidateInvalidEntries(@Source("src/test/resources/testData/userRequest/emptyRequest.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(6, violations.size());
	}
	

	@Test
	public void shouldAcceptValidEntries(@Source("src/test/resources/testData/userRequest/validUsers.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void shouldValidateInvalidEmailIds(@Source("src/test/resources/testData/userRequest/invalidEmail.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void shouldValidatePassword(@Source("src/test/resources/testData/userRequest/invalidPassword.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void shouldValidateFirstName(@Source("src/test/resources/testData/userRequest/invalidFirstName.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	

	@Test
	public void shouldValidateLastName(@Source("src/test/resources/testData/userRequest/invalidLastName.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void shouldValidateOrganization(@Source("src/test/resources/testData/userRequest/invalidOrganization.csv") final UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}

	
}


