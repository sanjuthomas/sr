package com.scrumretro.exception;

import com.scrumretro.enums.ResponseStatus;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class AuthorizationException extends ScrumRetroException{

    private static final long serialVersionUID = 1L;
    
    public AuthorizationException(){
    	this(ResponseStatus.ACCESS_DENIED.getCode(), ResponseStatus.ACCESS_DENIED.getReasonPhrase());
    }
	
	public AuthorizationException(final Integer errorCode, final String message){
		super(errorCode, message);
	}

	
}
