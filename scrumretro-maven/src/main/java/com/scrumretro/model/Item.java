package com.scrumretro.model;

import org.hibernate.validator.constraints.NotEmpty;
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

	@NotEmpty
	private User user;
	
	private Integer votes;
	
	@NotEmpty
	private Retrospective retrospective;
	

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
	
}
