package com.scrumretro.rest;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.scrumretro.enums.ResponseStatus;

/**
 * 
 * @author Sanju Thomas
 * 
 */

public class Response {

	private ResponseStatus responseStatus = ResponseStatus.SUCCESS;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(final ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
