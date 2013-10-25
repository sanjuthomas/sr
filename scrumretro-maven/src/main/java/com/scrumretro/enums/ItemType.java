package com.scrumretro.enums;

/**
 * This enum shall list all possible type of items.
 * 
 * @author Sanju Thomas
 *
 */
public enum ItemType {
	
	STOP_DOING("Stop Doing"), 
	START_DOING("Start Doing"), 
	CONTINUE_DOING("Continue Doing");
	
	private String displayString;
	
	public String getDisplayString() {
		return displayString;
	}

	ItemType(final String displayString){
		this.displayString = displayString;
	}

}
