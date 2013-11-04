package com.scrumretro.repository.model;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserDetail {
	
	private String firstName;
	
	private String lastName;
	
	private String organization;
	

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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}
	
}
