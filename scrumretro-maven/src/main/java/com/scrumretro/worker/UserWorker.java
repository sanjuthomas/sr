package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.util.EncryptionUtil;
import com.scrumretro.web.model.UserResponse;

public class UserWorker {

	@Autowired
	private UserRepository userRepository;
	
	
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserResponse findByUserIdAndPassword(final String userId,final String password){
		final User user = userRepository.findByUserIdAndPassword(userId,EncryptionUtil.encryptPassword(password));
		final UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		BeanUtils.copyProperties(user.getUserDetail(), userResponse);
		return userResponse;
	}
	
	public UserResponse findByUserId(final String userId){
		final User user = userRepository.findByUserId(userId);
		final UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		BeanUtils.copyProperties(user.getUserDetail(), userResponse);
		return userResponse;
	}
}
