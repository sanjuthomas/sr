package com.scrumretro.rest;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.scrumretro.enums.MessageType;
import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Message {
	
	private MessageType messageType;
	
	private String userId;
	
	private Project project;

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(final MessageType messageType) {
		this.messageType = messageType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}
	
	@Override
	public String toString(){
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this.messageType.getDescription());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
