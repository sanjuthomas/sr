package com.scrumretro.exception;

public class DuplicateVoteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateVoteException(String message){
		super(message);
	}

}
