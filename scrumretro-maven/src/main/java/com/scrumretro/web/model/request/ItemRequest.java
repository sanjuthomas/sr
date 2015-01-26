package com.scrumretro.web.model.request;

import com.scrumretro.enums.ItemType;
import com.scrumretro.rest.Request;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ItemRequest extends Request{
	
	private String retrospectiveId;
	
	private String description;
	
	private ItemType itemType;

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

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(final ItemType itemType) {
		this.itemType = itemType;
	}

}
