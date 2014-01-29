package com.scrumretro.security.authentication;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.web.service.UserService;

/**
 * 
 * @author Ragil
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-root-context.xml", "classpath:test-servlet-context.xml", "classpath:test-security-context.xml" })
public class TestAuthentication {

	@Mock
	private AuthenticationManager mockAuthenticationManager;
	
	@Mock
	private Authentication mockAuthentication;
	
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;
	
	@Autowired
    private FilterChainProxy filterChainProxy;
	
	@Before
	public void setUp() {
		initMocks(this);
		when(mockAuthenticationManager.authenticate(any(Authentication.class))).thenAnswer(new Answer<Authentication>() {
			 public Authentication answer(InvocationOnMock invocation) {
			     final Object[] args = invocation.getArguments();
			     final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			    		 (UsernamePasswordAuthenticationToken)args[0];
			     if(!"password".equals(usernamePasswordAuthenticationToken.getCredentials().toString())){
			    	  throw new BadCredentialsException("incorrect password");
			     }
			     return mockAuthentication;
			 }});
		
		for(final SecurityFilterChain securityFilterChain : filterChainProxy.getFilterChains()){
			for(final Filter filter : securityFilterChain.getFilters()){
				if(filter instanceof UsernamePasswordAuthenticationFilter){
					((UsernamePasswordAuthenticationFilter)filter).setAuthenticationManager(mockAuthenticationManager);
				}
			}
		}
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService).addFilter(this.filterChainProxy).build();
	}
	
	@Test
    public void shouldAuthenticate() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "password")).andExpect(status().isOk());
	}
	
	@Test
    public void shouldNotAuthenticate() throws Exception {
		mockMvc.perform(post("/login").param("username", "info@scrumretro.com")
			                    .param("password", "passwordwrong")).andExpect(status().isUnauthorized());
	}
	
}

