package com.scrumretro.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.scrumretro.rest.Request;

/**
 * User Registration Request Object.
 * 
 * @author Sanju Thomas
 *
 */
public class UserRequest extends Request{
	
	@NotEmpty()
	@Email()
	private String userId;
	
	@NotEmpty()
	@Size(min=4, max=16)
	private String password;
	
	@NotEmpty()
	@Size(max=32)
	private String firstName;
	
	@NotEmpty()
	@Size(max=32)
	private String lastName;
	
	@NotEmpty()
	@Size(max=64)
	private String organization;

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
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
	
	
	public String toString(){
		return super.toJSON();
	}
	
}