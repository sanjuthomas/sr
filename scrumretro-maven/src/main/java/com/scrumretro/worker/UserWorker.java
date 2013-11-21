package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.util.EncryptionUtil;
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
	 * This method shall accept userid and password and find the matching user.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public UserResponse findByUserIdAndPassword(final String userId, final String password){
		final User user = userRepository.findByUserIdAndPassword(userId,EncryptionUtil.encryptPassword(password));
		return createUserResponse(user);
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
