package com.scrumretro.repository.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scrumretro.enums.ItemType;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@Document(collection = "item")
public class Item {

	@Id
	private String id;

	private String userId;
	
	private List<String> votedUsers;
	
	private String retrospectiveId;
	
	private String description;
	
	private ItemType itemType;
	
	private Boolean active;
	

	public Boolean getActive() {
		return active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(final ItemType itemType) {
		this.itemType = itemType;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	
	public List<String> getVotedUsers() {
		return votedUsers;
	}

	public void setVotedUsers(final List<String> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getRetrospectiveId() {
		return retrospectiveId;
	}

	public void setRetrospectiveId(final String retrospectiveId) {
		this.retrospectiveId = retrospectiveId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
	public Integer getVoteCount(){
		if(null != this.votedUsers){
			return this.votedUsers.size();
		}
		return 0;
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
		final Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
