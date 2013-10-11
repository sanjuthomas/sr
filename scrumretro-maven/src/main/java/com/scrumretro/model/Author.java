package com.scrumretro.model;

/**
 * Domain object for Author type
 * 
 * @author Sanju Thomas
 *
 */

public class Author {
	
	private String id;
	
	private String firstName;
	
	private String lastName;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	

}
