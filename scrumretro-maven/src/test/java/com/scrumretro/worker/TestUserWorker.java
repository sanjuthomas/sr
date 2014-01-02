package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.enums.EmailType;
import com.scrumretro.repository.EmailRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Email;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.model.UserPasswordResetRequest;
import com.scrumretro.web.model.UserRegistrationRequest;
import com.scrumretro.web.model.UserResponse;

public class TestUserWorker {

	private UserWorker userWorker;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private EmailRepository mockEmailRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		final User user = createUser();
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(user);
		when(mockUserRepository.save(any(User.class))).thenReturn(user);
		userWorker = new UserWorker();
		userWorker.setUserRepository(mockUserRepository);
		userWorker.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		when(mockEmailRepository.findById(any(String.class))).thenReturn(createEmail());
		userWorker.setEmailRepository(mockEmailRepository);
	}
	
	@Test
	public void shouldSaveUser(){
		validateUserResponse(userWorker.save(createUserRequest()));
	}
	
	@Test
	public void shouldRestPassword() throws Exception{
		final UserPasswordResetRequest userPasswordResetRequest = new UserPasswordResetRequest();
		userPasswordResetRequest.setOldPassword("password");
		userPasswordResetRequest.setNewPassword("newPassword");
		userPasswordResetRequest.setNewconfirmPassword("newPassword");
		validateUserResponse(userWorker.resetPassword(userPasswordResetRequest, "info@scrumretro.com"));
	}
	
	
	private void validateUserResponse(final UserResponse userResponse){
		assertNotNull(userResponse);
		assertEquals("info@scrumretro.com", userResponse.getUserId());
		assertEquals("firstName", userResponse.getFirstName());
		assertEquals("lastName", userResponse.getLastName());
		assertEquals("lastName, firstName", userResponse.getDisplayName());
	}
	
	private UserRegistrationRequest createUserRequest(){
		final UserRegistrationRequest userRequest = new UserRegistrationRequest();
		userRequest.setUserId("sanju.thomas@ge.com");
		userRequest.setPassword("safe");
		return userRequest;
	}
	
	@Test
	public void shouldFindByUserId(){
		validateUserResponse(userWorker.findByUserId("info@scrumretro.com"));
	}
	
	@Test
	public void shouldActivateUser(){
		assertEquals(true, userWorker.activate("100567YTH985"));
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword(bCryptPasswordEncoder.encode("password"));
		user.setActive(true);
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
	
	private Email createEmail(){
		final Email email = new Email();
		email.setEmailType(EmailType.USER_REGISTRATION);
		email.setToAddress("newuser@scrumretro.com");
		email.setId("100567YTH985");
		return email;
	}
}
