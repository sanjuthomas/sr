package com.scrumretro.enums;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum ResponseStatus {
	
	
	SUCCESS("OK", 200),
	BAD_REQUEST("INVALID_INPUT", 400),
	PRECONDITION_FAILED("PRECONDITION_FAILED", 412),
	INVALID_CREDETIAL("INVALID_CREDETIAL", 49),
	INACTIVE_ACCOUNT("INACTIVE_ACCOUNT", 1900);
	
	private String messageKey;
	
	private Integer errorCode;
	
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(final String messageKey) {
		this.messageKey = messageKey;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(final Integer errorCode) {
		this.errorCode = errorCode;
	}
	

	private ResponseStatus(final String messageKey, final Integer errorCode){
		this.messageKey = messageKey;
		this.errorCode = errorCode;
	}		
	
}
