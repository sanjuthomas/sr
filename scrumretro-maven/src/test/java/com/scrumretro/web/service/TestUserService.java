package com.scrumretro.web.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.rest.Response;
import com.scrumretro.web.model.UserRegistrationRequest;
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
		when(userWorker.save(any(UserRegistrationRequest.class))).thenReturn(createUserResponse());
		userService.setUserWorker(userWorker);
	}
	
	@Test
	public void shouldFindByUserId() throws Exception{
		mockMvc.perform(get("/user/findById/{id}", "userId"))
		.andExpect(status().isOk())
		.andExpect(
				content().contentType(Response.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$firstName", is("firstName")))
		.andExpect(jsonPath("$lastName", is("lastName")))
		.andExpect(jsonPath("$organization", is("organization")))
		.andExpect(jsonPath("$userId", is("info@scumretro.com")));
	}
	
	@Test
	public void shouldRegisterUser() throws Exception{
		this.mockMvc.perform(
				post("/user/register/").content(createUserRegistrationRequest().toString())
						.contentType(Response.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$firstName", is("firstName")))
				.andExpect(jsonPath("$lastName", is("lastName")))
				.andExpect(jsonPath("$organization", is("organization")))
				.andExpect(jsonPath("$userId", is("info@scumretro.com")));
	}
	
	
	private UserResponse createUserResponse(){
		final UserResponse userResponse = new UserResponse();
		userResponse.setFirstName("firstName");
		userResponse.setLastName("lastName");
		userResponse.setOrganization("organization");
		userResponse.setUserId("info@scumretro.com");
		return userResponse;
	}
	
	private UserRegistrationRequest createUserRegistrationRequest(){
		final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
		userRegistrationRequest.setFirstName("firstName");
		userRegistrationRequest.setLastName("lastName");
		userRegistrationRequest.setOrganization("organization");
		userRegistrationRequest.setPassword("password");
		userRegistrationRequest.setConfirmPassword("password");
		userRegistrationRequest.setUserId("info@scumretro.com");
		return userRegistrationRequest;
	}
}
