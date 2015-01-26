package com.scrumretro.enums;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum ScrumRetroRoles {

	AUTHENTICATED_USER("AUTHENTICATED_USER", "Authenticated User"),
	DEVELOPER ("DEVELOPER", "Developer"),
	SCRUM_MASTER ("SCRUM_MASTER", "Scrum Master");
	
	
	private final String key;
	
	private final String description;
	
	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	private ScrumRetroRoles(final String key, final String description){
		this.key = key;
		this.description = description;
	}
}
