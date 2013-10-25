package com.scrumretro.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@Document(collection = "item")
public class Item {

	@Id
	private String id;

	private User user;
	
	private Integer votes;
	
	private Retrospective retrospective;
	
	private String description;
	

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(final Integer votes) {
		this.votes = votes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Retrospective getRetrospective() {
		return retrospective;
	}

	public void setRetrospective(final Retrospective retrospective) {
		this.retrospective = retrospective;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
}
