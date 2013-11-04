package com.scrumretro.web.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 *
 */
public class ItemResponse {
	
	private String id;
	
	@NotEmpty
	private String description;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
}
