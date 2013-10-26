package com.scrumretro.repository.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBObject;
import com.scrumretro.repository.model.User;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserWriteConverterBehavior {

	private UserWriteConverter userWriteConverter;
	
	@Before
	public void setUp(){
		userWriteConverter = new UserWriteConverter();
	}
	
	@Test
	public void shouldConvertUserCorrectly(){
		final DBObject dbObject = userWriteConverter.convert(createUser());
		assertEquals("info@scrumretro.com", dbObject.get("_id"));
	}
	
	private User createUser(){
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		return user;
	}
	
}
