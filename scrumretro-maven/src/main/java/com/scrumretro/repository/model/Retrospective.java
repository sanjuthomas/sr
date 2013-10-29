package com.scrumretro.repository.model;

import java.sql.Timestamp;

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
	
	private String name;
	
	private User user;
	
	private Project project;
	
	private Timestamp meetingTime;

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

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

	public Timestamp getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(final Timestamp meetingTime) {
		this.meetingTime = meetingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Retrospective other = (Retrospective) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
