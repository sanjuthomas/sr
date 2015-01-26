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
		assertEquals("AUTHENTICATED_USER", ScrumRetroRoles.AUTHENTICATED_USER.getKey());
		assertEquals("DEVELOPER", ScrumRetroRoles.DEVELOPER.getKey());
		assertEquals("SCRUM_MASTER", ScrumRetroRoles.SCRUM_MASTER.getKey());
	}
}
