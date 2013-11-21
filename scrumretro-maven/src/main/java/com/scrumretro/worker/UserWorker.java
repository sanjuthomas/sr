package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.model.UserRequest;
import com.scrumretro.web.model.UserResponse;

public class UserWorker {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void setbCryptPasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	/**
	 * This method shall accept a UserRequest object and save it to db.
	 * 
	 * @param userRequest
	 * @return
	 */
	public UserResponse save(final UserRequest userRequest){
		final User user = userRepository.save(createUser(userRequest));
		return createUserResponse(user);
	}
	
	private User createUser(final UserRequest userRequest){
		final User user  = new User();
		user.setActive(false);
		user.setUserId(userRequest.getUserId());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName(userRequest.getFirstName());
		userDetail.setLastName(userRequest.getLastName());
		userDetail.setOrganization(userRequest.getOrganization());
		user.setUserDetail(userDetail);
		return user;
	}
	
	/**
	 * This method shall take userid to find the matching user.
	 * 
	 * @param userId
	 * @return
	 */
	public UserResponse findByUserId(final String userId){
		final User user = userRepository.findByUserId(userId);
		return createUserResponse(user);
	}
	
	private UserResponse createUserResponse(final User user){
		final UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		BeanUtils.copyProperties(user.getUserDetail(), userResponse);
		return userResponse;
	}
}
