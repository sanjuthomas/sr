package com.scrumretro.enums;

/**
 * In addition to org.springframework.http.HttpStatus we need few more custom
 * application related status codes.
 * 
 * 
 * @author Sanju Thomas
 * 
 */
public enum ResponseStatus {

	UNKNOWN_ERROR(1900, "System error. Please report to support@scrumretro.com"),
	DUPLICATE_VOTE(1901, "Duplicate Vote"), 
	INACTIVE_ACCOUNT(1902,"Inactive Account"),
	USER_NOT_FOUND(1903, "User Doesn't Exist"),
	ACCESS_DENIED(1904, "Access to requested resource is denied");

	private final int code;

	private final String reasonPhrase;

	public int getCode() {
		return code;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	private ResponseStatus(final int code, final String reasonPhrase) {
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

}
