package com.scrumretro.web.service;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.web.model.UserResponse;
import com.scrumretro.worker.UserWorker;

/**
 * 
 * @author Sanju Thomas
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class TestUserService {
	
	@Mock
	private UserWorker userWorker;

	@Autowired
	private UserService userService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
		when(userWorker.findByUserId(any(String.class))).thenReturn(createUserResponse());
	}
	
	@Test
	public void shouldFindByUserId(){
		
	}
	
	
	private UserResponse createUserResponse(){
		final UserResponse userResponse = new UserResponse();
		userResponse.setFirstName("firstName");
		userResponse.setLastName("lastName");
		userResponse.setOrganization("organization");
		userResponse.setUserId("userId");
		return userResponse;
	}
	
	
}
