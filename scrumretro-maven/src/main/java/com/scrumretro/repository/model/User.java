package com.scrumretro.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Document(collection = "user")
public class User extends BaseDocument{
	
	@Id
	private String userId;
	
	private String password;
	
	private Boolean active;
	
	private UserDetail userDetail;
	

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(final UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
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
		return "User [userId=" + userId + "]";
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
