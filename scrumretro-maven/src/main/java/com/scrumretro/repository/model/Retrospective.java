package com.scrumretro.repository.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scrumretro.enums.RetrospectiveStatus;

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
	
	private String projectId;
	
	private Timestamp meetingTime;
	
	private RetrospectiveStatus retrospectiveStatus;

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

	public Timestamp getMeetingTime() {
		return (Timestamp) meetingTime.clone();
	}

	public void setMeetingTime(final Timestamp meetingTime) {
		this.meetingTime = (Timestamp) meetingTime.clone();
	}
	
	public RetrospectiveStatus getRetrospectiveStatus() {
		return retrospectiveStatus;
	}

	public void setRetrospectiveStatus(final RetrospectiveStatus retrospectiveStatus) {
		this.retrospectiveStatus = retrospectiveStatus;
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
