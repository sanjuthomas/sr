package com.scrumretro.exception;

import com.scrumretro.enums.ResponseStatus;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserNotFoundException extends ScrumRetroException{

	public UserNotFoundException (){
		this(ResponseStatus.USER_NOT_FOUND.getCode(), ResponseStatus.USER_NOT_FOUND.getReasonPhrase());
	}
	
	public UserNotFoundException(Integer errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
