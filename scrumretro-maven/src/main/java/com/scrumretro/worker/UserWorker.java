package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.enums.EmailType;
import com.scrumretro.repository.EmailRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Email;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.util.UIDGenerator;
import com.scrumretro.web.model.UserRegistrationRequest;
import com.scrumretro.web.model.UserResponse;

public class UserWorker {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void setbCryptPasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void setEmailRepository(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}
	
	/**
	 * This method shall accept a UserRequest object and save it to db.
	 * 
	 * @param userRequest
	 * @return
	 */
	public UserResponse save(final UserRegistrationRequest userRequest){
		final User user = userRepository.save(createUser(userRequest));
		Email email = new Email();
		email.setEmailType(EmailType.USER_REGISTRATION);
		email.setId(new UIDGenerator().getValue());
		email.setToAddress(user.getUserId());
		return createUserResponse(user);
	}
	
	private User createUser(final UserRegistrationRequest userRequest){
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
	
	/**
	 * This method shall accept a uId and activate user.
	 * 
	 * @param uId
	 * @return
	 */
	public boolean activate(final String uId){
		final Email email = emailRepository.findById(uId);
		User user = userRepository.findByUserId(email.getToAddress());
		user.setActive(true);
		user = userRepository.save(user);
		return user.getActive();
	}
	
	private UserResponse createUserResponse(final User user){
		final UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		BeanUtils.copyProperties(user.getUserDetail(), userResponse);
		return userResponse;
	}
}
