package com.scrumretro.exception;

public class InvalidPathException extends ScrumRetroException {

	private static final long serialVersionUID = 1L;

	public InvalidPathException() {
		super();
	}

	public InvalidPathException(String message) {
		super(message);

	}

	public InvalidPathException(Throwable cause) {
		super(cause);

	}

	public InvalidPathException(String message, Throwable cause) {
		super(message, cause);

	}

}
