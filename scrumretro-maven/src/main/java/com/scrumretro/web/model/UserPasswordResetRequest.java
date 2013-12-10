package com.scrumretro.web.model;

import com.scrumretro.rest.Request;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserPasswordResetRequest extends Request{
	
	private String oldPassword;
	
	private String newPassword;
	
	private String newConfirmationPassword;

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

	public String getNewConfirmationPassword() {
		return newConfirmationPassword;
	}

	public void setNewConfirmationPassword(final String newConfirmationPassword) {
		this.newConfirmationPassword = newConfirmationPassword;
	}
	
}
