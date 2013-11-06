package com.scrumretro.web.model;

import com.scrumretro.rest.Response;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class ItemResponse extends Response{
	
	private String id;
	
	private String description;
	
	private String ownerDispalyName;
	
	private String itemTypeDisplayString;
	
	private String retrospectiveName;
	
	private String projectName;
	
	private String createdTime;

	
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getOwnerDispalyName() {
		return ownerDispalyName;
	}

	public void setOwnerDispalyName(final String ownerDispalyName) {
		this.ownerDispalyName = ownerDispalyName;
	}

	public String getItemTypeDisplayString() {
		return itemTypeDisplayString;
	}

	public void setItemTypeDisplayString(final String itemTypeDisplayString) {
		this.itemTypeDisplayString = itemTypeDisplayString;
	}

	public String getRetrospectiveName() {
		return retrospectiveName;
	}

	public void setRetrospectiveName(final String retrospectiveName) {
		this.retrospectiveName = retrospectiveName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(final String createdTime) {
		this.createdTime = createdTime;
	}
	
}
