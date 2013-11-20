package com.scrumretro.exception;

public class AuthorizationException extends RuntimeException{

    private static final long serialVersionUID = 1L;
	
	public AuthorizationException(final String message){
		super(message);
	}
}
