package com.scrumretro.web.model;

/**
 * 
 * @author Sanju Thomas
 * 
 */
public class ProjectResponse {
	
	private String id;
	private String name;
	private String description;
	private String organization;
	private String ownerDisplayName;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(final String organization) {
		this.organization = organization;
	}
	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}
	public void setOwnerDisplayName(final String ownerDisplayName) {
		this.ownerDisplayName = ownerDisplayName;
	}
	
}
