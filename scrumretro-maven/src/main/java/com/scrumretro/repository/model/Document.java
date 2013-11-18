package com.scrumretro.repository.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Document {
	

	@Version
	private Long version;
	
	@CreatedDate
    private DateTime created;
	
    @LastModifiedDate
    private DateTime lastModified;
    
    @CreatedBy
    private String createdBy;
     
    @LastModifiedBy
    private String lastModifiedBy;

	public Long getVersion() {
		return version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(final DateTime created) {
		this.created = created;
	}

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(final DateTime lastModified) {
		this.lastModified = lastModified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
    
}
