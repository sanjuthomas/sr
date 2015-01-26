package com.scrumretro.web.model.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.scrumretro.enums.ScrumRetroRoles;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserUpdateRequest {
	
	@NotEmpty(message ="userId may not be empty!")
	@Email()
	private String userId;
	
	private ScrumRetroRoles scrumRetroRole;
	
	@NotEmpty(message ="userId may not be empty!")
	private String projectId;
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}

	public void setScrumRetroRole(final ScrumRetroRoles scrumRetroRole) {
		this.scrumRetroRole = scrumRetroRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public ScrumRetroRoles getScrumRetroRole() {
		return scrumRetroRole;
	}

	public void setScrumRetroRoles(final ScrumRetroRoles scrumRetroRole) {
		this.scrumRetroRole = scrumRetroRole;
	}
}
