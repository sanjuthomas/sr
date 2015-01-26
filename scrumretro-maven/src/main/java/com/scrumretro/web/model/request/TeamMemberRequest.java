package com.scrumretro.web.model.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Sanju Thomas
 * 
 */
public class TeamMemberRequest {

	
	@NotEmpty(message ="firstName may not be empty!")
	@Size(max=32)
	private String firstName;
	
	@NotEmpty(message ="lastName may not be empty!")
	@Size(max=32)
	private String lastName;
	
	@NotEmpty(message ="userId may not be empty!")
	@Email()
	private String email;
	
	@NotEmpty(message ="sponsor may not be empty!")
	@Email()
	
	private String sponsor;
	
	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(final String sponsor) {
		this.sponsor = sponsor;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
}
