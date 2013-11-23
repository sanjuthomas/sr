package com.scrumretro.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.web.model.UserResponse;
import com.scrumretro.worker.UserWorker;

/**
 * This class encapsulate all the service methods associate with User
 * document.
 * 
 * @author Ragil
 * 
 */
@Controller
public class UserService {

	@Autowired
	private UserWorker userWorker;

	public void setUserWorker(final UserWorker userWorker) {
		this.userWorker = userWorker;
	}
	/**
	 * This service shall return the current user details
	 * in json format.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/currentUser", method = RequestMethod.GET)
	@ResponseBody
	public UserResponse currentUser(final Authentication authentication) {
		 final ScrumRetroUser currentUser =  (ScrumRetroUser)authentication.getPrincipal();
		 final UserResponse userResponse = new UserResponse();
		 BeanUtils.copyProperties(currentUser, userResponse);
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
}
