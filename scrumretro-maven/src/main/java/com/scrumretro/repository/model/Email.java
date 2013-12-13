package com.scrumretro.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scrumretro.enums.EmailType;
/**
 * 
 * @author Ragil
 * 
 */
@Document(collection = "email")
public class Email {

	@Id
	private String id;
	
	private EmailType emailType;
	
	private String emailSubject;
	
	private String toAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmailType getEmailType() {
		return emailType;
	}

	public void setEmailType(EmailType emailType) {
		this.emailType = emailType;
		this.emailSubject = emailType.getSubject();
	}

	public String getEmailSubject() {
		return this.emailSubject;
		
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
}
