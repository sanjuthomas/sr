package com.scrumretro.rest;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Request {
	
	/**
	 * Convert this object to json.
	 * 
	 * @param object
	 * @return
	 */
	protected String toJSON(){
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
