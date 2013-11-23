package com.scrumretro.web.model;

import static org.junit.Assert.assertTrue;

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
	public void shouldValidateInvalidEntries(@Source("src/test/resources/testData/userRequest/invalidUsers.csv") UserRequest userRequest) {
		final Set<ConstraintViolation<UserRequest>> violations = validator
				.validate(userRequest);
		assertTrue(violations.size() > 1);
	}

}
