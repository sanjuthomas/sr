package com.scrumretro.enums;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum ScrumRetroRoles {

	ROLE_USER ("ROLE_USER", "Authenticated User");
	
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
