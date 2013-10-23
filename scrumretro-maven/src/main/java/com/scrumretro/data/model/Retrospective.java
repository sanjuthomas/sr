package com.scrumretro.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Document(collection = "retrospective")
public class Retrospective {
	
	@Id
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private User user;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
