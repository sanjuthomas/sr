package com.scrumretro.rest;

import com.scrumretro.enums.ResponseStatus;


/**
 * 
 * @author Sanju Thomas
 *
 */

public class Response {
	
	private ResponseStatus responseStatus;

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(final ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
