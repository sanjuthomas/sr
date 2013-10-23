package com.scrumretro.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Document(collection = "user")
public class User {
	
	private String emailId;
	
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
