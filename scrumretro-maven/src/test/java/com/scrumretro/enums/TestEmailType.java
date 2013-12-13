package com.scrumretro.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * 
 * @author Ragil
 *
 */
public class TestEmailType {

	@Test
	public void shouldReturnEmailTypeId(){
		assertEquals(100, EmailType.USER_REGISTRATION.getTypeId());
	}
}
