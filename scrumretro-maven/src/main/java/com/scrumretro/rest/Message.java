package com.scrumretro.rest;

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

}
