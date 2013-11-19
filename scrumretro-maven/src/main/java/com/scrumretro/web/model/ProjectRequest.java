package com.scrumretro.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.scrumretro.rest.Request;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ProjectRequest extends Request{
	
	@Size(max=256)
	private String id;
	
	@NotEmpty 
	@Size(max=512)
	private String name;
	
	@Size(max=32768)
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return super.toJSON();
	}
}
