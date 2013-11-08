package com.scrumretro.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestResponse {
	
	@Test
	public void shouldBeSuccessResponse(){
		final Response response = new Response();
		assertEquals("OK", response.getResponseStatus().getMessageKey());
	}

}
