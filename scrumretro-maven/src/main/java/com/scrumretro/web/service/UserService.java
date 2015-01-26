package com.scrumretro.web.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.rest.Service;
import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.web.model.request.UserPasswordResetRequest;
import com.scrumretro.web.model.request.UserRegistrationRequest;
import com.scrumretro.web.model.response.UserResponse;
import com.scrumretro.worker.UserWorker;

/**
 * This class encapsulate all the service methods associate with User document.
 * 
 * @author Ragil
 * 
 */
@Controller
public class UserService extends Service{

	@Autowired
	private UserWorker userWorker;

	public void setUserWorker(final UserWorker userWorker) {
		this.userWorker = userWorker;
	}

	/**
	 * This service shall return the current user details in json format.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/userProfile/", method = RequestMethod.GET)
	@ResponseBody
	public UserResponse userprofile(final Authentication authentication) {
		final ScrumRetroUser currentUser = (ScrumRetroUser) authentication.getPrincipal();
		final UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(currentUser, userResponse);
		userResponse.setUserId(currentUser.getUsername());
		return userResponse;
	}

	/**
	 * This service shall take user id request parameter and return the
	 * UserResponse in json format.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserResponse findByUserId(@PathVariable("id") final String id) {
		return userWorker.findByUserId(id);
	}
	
	/**
	 * This service shall take user json as request and save the given user.
	 * This method shall return UserResponse in json format.
	 * 
	 * @param userRegistrationRequest
	 * @return
	 */
	@RequestMapping(value = "/user/register/", method = RequestMethod.POST)
	@ResponseBody
	public UserResponse register(@Valid @RequestBody final UserRegistrationRequest userRegistrationRequest) {
		return userWorker.save(userRegistrationRequest);
	}

	/**
	 * This service shall take the password reset request and send back
	 * 
	 * @param userPasswordResetRequest
	 * @return
	 */
	@RequestMapping(value = "/user/resetPassword/", method = RequestMethod.POST)
	@ResponseBody
	public UserResponse resetPassword(@Valid @RequestBody final UserPasswordResetRequest userPasswordResetRequest,
			final Authentication authentication) {
		final ScrumRetroUser currentUser = (ScrumRetroUser) authentication.getPrincipal();
		return userWorker.resetPassword(userPasswordResetRequest, currentUser.getUsername());
	}

}
