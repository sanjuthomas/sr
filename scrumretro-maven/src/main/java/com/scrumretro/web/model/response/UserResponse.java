package com.scrumretro.web.model.response;

import com.scrumretro.rest.Response;

/**
 * 
 * @author Ragil
 *
 */
public class UserResponse extends Response{

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String organization;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}

	public String getDisplayName() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.getLastName());
		builder.append(", ");
		builder.append(this.getFirstName());
		return builder.toString();
	}
}
