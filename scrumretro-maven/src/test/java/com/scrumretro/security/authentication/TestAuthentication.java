package com.scrumretro.security.authentication;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml","classpath:test-applicationSecurity.xml" })
public class TestAuthentication {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ScrumRetroUserDetailsAuthenticationProvider scrumRetroUserDetailsAuthenticationProvider;
	
	@Mock
	private UserRepository mockUserRepository;
	
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;
	
	@Autowired
    private UsernamePasswordAuthenticationFilter filter;
	
	@Before
	public void setUp() {
		initMocks(this);
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(createUser());
		scrumRetroUserDetailsAuthenticationProvider.setUserRepository(mockUserRepository);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService).addFilter(this.springSecurityFilterChain).build();
	}
	@Test
    public void successLogin() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "password")).andExpect(status().isOk());
	}
	
	@Test
    public void failLogin() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "passwordwrong")).andExpect(status().isUnauthorized());
	}
	

	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword("$2a$10$EzO6FnN.E1hFt18nI6BjfuRsQvavkeOWmC.PeUnaB54NpZDYXoGXC");
		user.setActive(true);
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
	
	
}

