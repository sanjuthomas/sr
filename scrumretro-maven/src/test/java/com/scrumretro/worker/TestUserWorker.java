package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.model.UserResponse;

public class TestUserWorker {

private UserWorker userWorker;
	
	@Mock
	private UserRepository mockUserRepository;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		final User user = createUser();
		when(mockUserRepository.findByUserIdAndPassword(any(String.class),any(String.class))).thenReturn(user);
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(user);
		userWorker = new UserWorker();
		userWorker.setUserRepository(mockUserRepository);
	}
	
	@Test
	public void shouldFindByUserIdAndPassword(){
		UserResponse userResponse = userWorker.findByUserIdAndPassword("info@scrumretro.com","password");
		assertNotNull(userResponse);
		assertEquals("info@scrumretro.com", userResponse.getEmailId());
		assertEquals("firstName", userResponse.getFirstName());
		assertEquals("lastName", userResponse.getLastName());
		assertEquals("lastName, firstName", userResponse.getDisplayName());
		
	}
	
	@Test
	public void shouldFindByUserId(){
		UserResponse userResponse = userWorker.findByUserId("info@scrumretro.com");
		assertNotNull(userResponse);
		assertEquals("info@scrumretro.com", userResponse.getEmailId());
		assertEquals("firstName", userResponse.getFirstName());
		assertEquals("lastName", userResponse.getLastName());
		assertEquals("lastName, firstName", userResponse.getDisplayName());
		
	}
	
	private User createUser(){
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		user.setPassword("password");
		user.setActive(true);
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
}
