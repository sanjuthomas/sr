package com.scrumretro.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestScrumRetroRoles {

	@Test
	public void shouldReturnKey(){
		assertEquals("ROLE_USER", ScrumRetroRoles.ROLE_USER.getKey());
	}
}
