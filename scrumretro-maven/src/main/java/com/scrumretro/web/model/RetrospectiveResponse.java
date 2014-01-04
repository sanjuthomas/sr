package com.scrumretro.web.model;

import com.scrumretro.rest.Response;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class RetrospectiveResponse extends Response{
	
	private String id;
	
	private String name;
	
	private String projectId;

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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}
	
}
