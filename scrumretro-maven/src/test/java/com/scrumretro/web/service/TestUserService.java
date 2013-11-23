package com.scrumretro.web.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.security.authentication.ScrumretroUserService;
import com.scrumretro.worker.UserWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml","classpath:test-applicationSecurity.xml" })
public class TestUserService {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ScrumretroUserService scrumretroUserService;
	
	@Mock
	private UserRepository mockUserRepository;
	
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;
	
	 @Autowired
     private FilterChainProxy springSecurityFilterChain;
	
	@Before
	public void setUp() {
		initMocks(this);
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(createUser());
		scrumretroUserService.setUserRepository(mockUserRepository);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService).addFilter(this.springSecurityFilterChain).build();
	}
	@Test
    public void successLogin() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "mypassword")).andExpect(status().isOk());
	}
	
	@Test
    public void failLogin() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "mypasswordwrong")).andExpect(status().isUnauthorized());
	}
	
	/*@Test
    public void authorizedAcess() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "mypassword")).andExpect(status().isOk());
		 mockMvc.perform(get("/user/currentUser"))
		.andExpect(status().isOk())
		.andExpect(
				content().contentType(Response.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$userId", is("info@scrumretro.com")));
	}*/
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword("mypassword");
		user.setActive(true);
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
	
}
