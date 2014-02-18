package com.scrumretro.rest;

import com.scrumretro.enums.MessageType;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Message {
	
	private MessageType messageType;
	
	private String userId;
	
	private String project;

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

	public String getProject() {
		return project;
	}

	public void setProject(final String project) {
		this.project = project;
	}

}
