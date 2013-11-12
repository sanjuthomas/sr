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

	DUPLICATE_VOTE(1901, "Duplicate Vote"), INACTIVE_ACCOUNT(1900,
			"Inactive Account");

	private final int value;

	private final String reasonPhrase;

	public int getValue() {
		return value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	private ResponseStatus(final int value, final String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

}
