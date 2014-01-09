package com.scrumretro.exception;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class AuthorizationException extends Exception{

    private static final long serialVersionUID = 1L;
    
    private Integer errorCode;
	
	public AuthorizationException(final Integer errorCode, final String message){
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	
}
