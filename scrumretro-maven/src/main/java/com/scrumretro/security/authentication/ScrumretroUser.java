package com.scrumretro.security.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ScrumretroUser extends User {

	/**
	 * 
	 * @author Ragil
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScrumretroUser(String username, String password, String firstName,
			String lastName, String organization,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);

		this.firstName = firstName;
		this.lastName = lastName;
		this.organization = organization;

	}

	private String firstName;
	private String lastName;
	private String organization;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
