package com.scrumretro.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.scrumretro.rest.Request;
import com.scrumretro.web.validation.ValidateConfirmationPassword;

/**
 * 
 * @author Sanju Thomas
 *
 */

@ValidateConfirmationPassword
public class UserPasswordResetRequest extends Request{
	
	@NotEmpty(message ="password may not be empty!")
	@Size(min=4, max=16)
	private String oldPassword;
	
	@NotEmpty(message ="newPassword may not be empty!")
	@Size(min=4, max=16)
	private String newPassword;
	
	@NotEmpty(message ="newConfirmPassword may not be empty!")
	@Size(min=4, max=16)
	private String newconfirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(final String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewconfirmPassword() {
		return newconfirmPassword;
	}

	public void setNewconfirmPassword(final String newconfirmPassword) {
		this.newconfirmPassword = newconfirmPassword;
	}
}
