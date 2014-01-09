package com.scrumretro.exception;

/**
 * 
 * @author Sanju Thomas
 * 
 */
public class DuplicateVoteException extends Exception{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer errorCode;
	
	public DuplicateVoteException(final Integer errorCode, final String message){
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
}
