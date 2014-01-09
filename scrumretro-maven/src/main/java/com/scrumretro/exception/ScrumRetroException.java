package com.scrumretro.exception;

public class ScrumRetroException extends Exception{
	
    private static final long serialVersionUID = 1L;
    
    private Integer errorCode;
    
    public ScrumRetroException(){}
	
	public ScrumRetroException(final Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final Integer errorCode) {
		this.errorCode = errorCode;
	}
}
