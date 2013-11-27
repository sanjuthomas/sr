package com.scrumretro.security.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 
 * @author Ragil
 * 
 */
public class ScrumRetroUser extends User {
	
	public static ScrumRetroUser UNKNOWN = new ScrumRetroUser("UNKNOWN", "UNKNOWN", false, 
			"UNKNOWN", "UNKNOWN", "UNKNOWN", new ArrayList< SimpleGrantedAuthority>());

	private static final long serialVersionUID = 1L;

	public ScrumRetroUser(final String username, final String password,
			final Boolean enabled, final String firstName, final String lastName,
			final String organization,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
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
