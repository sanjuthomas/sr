package com.scrumretro.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Document(collection = "user")
public class User {
	
	@Id
	private String emailId;
	
	private String password;
	
	private Boolean active;
	
	private UserDetail userDetail;
	

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(final UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [emailId=" + emailId + "]";
	}
	

	/**
	 * userDetail.lastName, userDetail.firstName
	 * 
	 * @return
	 */
	public String getDisplayName(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.userDetail.getLastName());
		builder.append(", ");
		builder.append(this.userDetail.getFirstName());
		return builder.toString();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		final User other = (User) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		return true;
	}

}
