package com.scrumretro.exception;

import com.scrumretro.enums.ResponseStatus;

/**
 * 
 * @author Sanju Thomas
 * 
 */
public class DuplicateVoteException extends ScrumRetroException{
	
	private static final long serialVersionUID = 1L;
	
	public DuplicateVoteException(){
		this(ResponseStatus.DUPLICATE_VOTE.getCode(), ResponseStatus.DUPLICATE_VOTE.getReasonPhrase());
	}
	
	public DuplicateVoteException(final Integer errorCode, final String message){
		super(errorCode, message);
	}

}
