package com.scrumretro.enums;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum ResponseStatus {
	
	DUPLICATE_VOTE("DUPLICATE_VOTE", 1901),
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
