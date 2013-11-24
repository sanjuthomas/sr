package com.scrumretro.security.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrumretro.enums.ScrumRetroRoles;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class TestScrumRetroUserDetailsAuthenticationProvider {
	
	@Autowired
	private ScrumRetroUserDetailsAuthenticationProvider scrumRetroUserDetailsAuthenticationProvider;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	
	@Mock
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Before
	public void setUp(){
		initMocks(this);
		scrumRetroUserDetailsAuthenticationProvider.setUserRepository(mockUserRepository);
		scrumRetroUserDetailsAuthenticationProvider.setbCryptPasswordEncoder(mockBCryptPasswordEncoder);
		when(usernamePasswordAuthenticationToken.getPrincipal()).thenReturn(new Object());
		when(usernamePasswordAuthenticationToken.getName()).thenReturn("info@scrumretro.com");
		when(usernamePasswordAuthenticationToken.getCredentials()).thenReturn("password");
		final User user = createUser();
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenReturn(user);
		when(mockBCryptPasswordEncoder.matches("password", "password")).thenReturn(true);
	}
	
	@Test
	public void shouldAuthenticate(){	
		final Authentication authentication = scrumRetroUserDetailsAuthenticationProvider.
				authenticate(usernamePasswordAuthenticationToken);
		assertNotNull(authentication);
		assertEquals("info@scrumretro.com", authentication.getName());
		assertEquals("password", authentication.getCredentials().toString());
		final ScrumRetroUser scrumRetroUser =(ScrumRetroUser) authentication.getPrincipal();
		assertEquals("firstName", scrumRetroUser.getFirstName());
		assertEquals("lastName", scrumRetroUser.getLastName());
		assertEquals("organization", scrumRetroUser.getOrganization());
		assertEquals("password", scrumRetroUser.getPassword());
		assertEquals("info@scrumretro.com", scrumRetroUser.getUsername());
		assertFalse(scrumRetroUser.getAuthorities().isEmpty());
		assertEquals(ScrumRetroRoles.ROLE_USER.getKey(), scrumRetroUser.getAuthorities().
				iterator().next().getAuthority());
	}
	
	@Test
	public void shouldFireRuntimeException(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(startsWith("Unknown error occurred while finding the user"));
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenThrow(new RuntimeException());		
		scrumRetroUserDetailsAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
	}
	
	@Test()
	public void shouldFireUsernameNotFoundException(){
		expectedException.expect(BadCredentialsException.class);
		expectedException.expectMessage(endsWith("Bad credentials"));
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenReturn(null);		
		scrumRetroUserDetailsAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
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
