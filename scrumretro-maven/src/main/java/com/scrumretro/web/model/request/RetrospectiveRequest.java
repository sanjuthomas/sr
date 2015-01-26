package com.scrumretro.web.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.scrumretro.rest.Request;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class RetrospectiveRequest extends Request {
	
	@Size(max=256)
	private String id;
	
	@NotEmpty 
	@Size(max=256)
	private String projectId;
	
	@NotEmpty 
	@Size(max=512)
	private String name;
	
	@NotNull
	private DateTime time;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(final DateTime time) {
		this.time = time;
	}

}
