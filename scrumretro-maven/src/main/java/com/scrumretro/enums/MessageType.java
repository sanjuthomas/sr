package com.scrumretro.enums;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum MessageType {
	
	VOTING_BEGINS("Retrospective is now open for voting");
	
	private String description;
	
	private MessageType(final String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}

}
