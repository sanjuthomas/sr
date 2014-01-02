package com.scrumretro.exception;

public class ScrumRetroException extends RuntimeException{

    private static final long serialVersionUID = 1L;
	
	public ScrumRetroException() {
		super();
	}
	public ScrumRetroException(String message) {
		super(message);
	}
	public ScrumRetroException(Throwable cause) {
		super(cause);
	}
	public ScrumRetroException(String message, Throwable cause) {
		super(message, cause);
	}
}
