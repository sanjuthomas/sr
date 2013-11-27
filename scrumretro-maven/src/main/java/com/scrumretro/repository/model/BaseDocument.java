package com.scrumretro.repository.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/**
 * This is the Base Document for all documents in ScrumRetro.com
 * Since documents are stored in the json format keys also consume some 
 * hard disk space. To reduce the size of the JSON we are using short 
 * code for all the audit trail fields. Since all the fields are annotated 
 * readability is not an issue here.
 * 
 * @author Sanju Thomas
 *
 */
public class BaseDocument {
	
	@Version
	private Long v;
	
	@CreatedDate
    private DateTime cd;
	
    @LastModifiedDate
    private DateTime lmd;
    
    @CreatedBy
    private String cb;
     
    @LastModifiedBy
    private String lmb;

	public Long getV() {
		return v;
	}

	public void setV(final Long v) {
		this.v = v;
	}

	public DateTime getCd() {
		return cd;
	}

	public void setCd(final DateTime cd) {
		this.cd = cd;
	}

	public DateTime getLmd() {
		return lmd;
	}

	public void setLmd(final DateTime lmd) {
		this.lmd = lmd;
	}

	public String getCb() {
		return cb;
	}

	public void setCb(final String cb) {
		this.cb = cb;
	}

	public String getLmb() {
		return lmb;
	}

	public void setLmb(final String lmb) {
		this.lmb = lmb;
	}
}
