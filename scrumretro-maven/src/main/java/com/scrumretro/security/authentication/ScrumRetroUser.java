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
	
	private static final String UNKNOWN = "UNKNOWN";
	
	public static final ScrumRetroUser UNKNOWN_USER = new ScrumRetroUser(UNKNOWN, UNKNOWN, false, 
			UNKNOWN, UNKNOWN, UNKNOWN, new ArrayList< SimpleGrantedAuthority>());

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

	final private String firstName;
	final private String lastName;
	final private String organization;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getOrganization() {
		return organization;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

}
