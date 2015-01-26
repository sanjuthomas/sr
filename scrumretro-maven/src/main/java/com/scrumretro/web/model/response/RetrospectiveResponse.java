package com.scrumretro.web.model.response;

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
	
	private String projectName;
	
	private String statusDisplayString;

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public String getStatusDisplayString() {
		return statusDisplayString;
	}

	public void setStatusDisplayString(String statusDisplayString) {
		this.statusDisplayString = statusDisplayString;
	}
	
}
