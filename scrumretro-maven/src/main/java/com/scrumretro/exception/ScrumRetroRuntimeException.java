package com.scrumretro.exception;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ScrumRetroRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private ScrumRetroException scrumRetroException;
	
	public ScrumRetroRuntimeException(final ScrumRetroException scrumRetroException){
		this.scrumRetroException = scrumRetroException;
	}
	
	public ScrumRetroRuntimeException(String message, Throwable t) {
		super(message, t);
	}

	public String getMessage(){
		return this.scrumRetroException.getMessage();
	}
	
	public Integer getErrorCode(){
		return this.scrumRetroException.getErrorCode();
	}
	
	
}
