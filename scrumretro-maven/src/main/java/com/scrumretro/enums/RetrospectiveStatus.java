package com.scrumretro.enums;

/**
 * A Retrospective can have three state.
 * 
 * 1. Open - in this state developers can add items
 * 2. Open for voting - voting is allowed, no more item
 * 3. Closed - no more voting, no more item.
 * 
 * @author Sanju Thomas
 *
 */
public enum RetrospectiveStatus {

	OPEN("Open"), 
	OPEN_FOR_VOTING("Open For Voting"), 
	CLOSED("Closed");
	
	private String displayString;
	
	public String getDisplayString() {
		return displayString;
	}

	RetrospectiveStatus(final String displayString){
		this.displayString = displayString;
	}
}
