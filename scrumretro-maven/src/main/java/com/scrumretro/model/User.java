package com.scrumretro.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Document(collection = "user")
public class User {
	
	@NotEmpty
	private String emailId;
	
	@NotEmpty
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
