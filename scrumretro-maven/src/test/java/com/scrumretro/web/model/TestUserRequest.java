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
	public void shouldValidateInvalidEntries(@Source("src/test/resources/testData/userRequest/emptyRequest.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(8, violations.size());
	}
	

	@Test
	public void shouldAcceptValidEntries(@Source("src/test/resources/testData/userRequest/validUsers.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void shouldValidateInvalidEmailIds(@Source("src/test/resources/testData/userRequest/invalidEmail.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void shouldValidatePassword(@Source("src/test/resources/testData/userRequest/invalidPassword.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(2, violations.size());
	}
	
	@Test
	public void shouldValidateFirstName(@Source("src/test/resources/testData/userRequest/invalidFirstName.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	

	@Test
	public void shouldValidateLastName(@Source("src/test/resources/testData/userRequest/invalidLastName.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void shouldValidateOrganization(@Source("src/test/resources/testData/userRequest/invalidOrganization.csv") final UserRegistrationRequest userRequest) {
		final Set<ConstraintViolation<UserRegistrationRequest>> violations = validator
				.validate(userRequest);
		assertEquals(1, violations.size());
	}

	
}


