package com.scrumretro.security.authentication;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.security.authentication.DisabledException;
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
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-security-context.xml" })
public class TestScrumRetroUserDetailsAuthenticationProvider {
	
	@Autowired
	private ScrumRetroUserDetailsAuthenticationProvider scrumRetroUserDetailsAuthenticationProvider;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	
	@Mock
	private UsernamePasswordAuthenticationToken mockUsernamePasswordAuthenticationToken;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Before
	public void setUp(){
		initMocks(this);
		scrumRetroUserDetailsAuthenticationProvider.setUserRepository(mockUserRepository);
		scrumRetroUserDetailsAuthenticationProvider.setbCryptPasswordEncoder(mockBCryptPasswordEncoder);
		when(mockUsernamePasswordAuthenticationToken.getPrincipal()).thenReturn(new Object());
		when(mockUsernamePasswordAuthenticationToken.getName()).thenReturn("info@scrumretro.com");
		when(mockUsernamePasswordAuthenticationToken.getCredentials()).thenReturn("password");
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenReturn(createUser(true));
		when(mockBCryptPasswordEncoder.matches("password", "password")).thenReturn(true);
	}
	
	@Test
	public void shouldAuthenticate(){	
		final Authentication authentication = scrumRetroUserDetailsAuthenticationProvider.
				authenticate(mockUsernamePasswordAuthenticationToken);
		assertNotNull(authentication);
		assertEquals("info@scrumretro.com", authentication.getName());
		assertEquals("password", authentication.getCredentials().toString());
		final ScrumRetroUser scrumRetroUser =(ScrumRetroUser) authentication.getPrincipal();
		assertEquals("firstName", scrumRetroUser.getFirstName());
		assertEquals("lastName", scrumRetroUser.getLastName());
		assertEquals("password", scrumRetroUser.getPassword());
		assertEquals("info@scrumretro.com", scrumRetroUser.getUsername());
		assertFalse(scrumRetroUser.getAuthorities().isEmpty());
		assertEquals(ScrumRetroRoles.AUTHENTICATED_USER.getKey(), scrumRetroUser.getAuthorities().
				iterator().next().getAuthority());
	}
	
	@Test
	public void shouldFireRuntimeException(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(startsWith("Unknown error occurred while finding the user"));
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenThrow(new RuntimeException());		
		scrumRetroUserDetailsAuthenticationProvider.authenticate(mockUsernamePasswordAuthenticationToken);
	}
	
	@Test()
	public void shouldFireBadCredentialsException(){
		expectedException.expect(BadCredentialsException.class);
		expectedException.expectMessage(endsWith("Bad credentials"));
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenReturn(null);		
		scrumRetroUserDetailsAuthenticationProvider.authenticate(mockUsernamePasswordAuthenticationToken);
	}
	
	@Test
	public void shouldFireBadCredentialsExceptionWhenCredentialIsNull(){
		expectedException.expect(BadCredentialsException.class);
		expectedException.expectMessage(startsWith("No password is provided for user"));
		when(mockUsernamePasswordAuthenticationToken.getCredentials()).thenReturn(null);
		scrumRetroUserDetailsAuthenticationProvider.authenticate(mockUsernamePasswordAuthenticationToken);
	}
	
	@Test
	public void shouldFireBadCredentialsExceptionWhenPasswordDoesntMatch(){
		expectedException.expect(BadCredentialsException.class);
		expectedException.expectMessage(startsWith("Incorrect password is provided for user"));
		when(mockBCryptPasswordEncoder.matches("password", "password")).thenReturn(false);
		scrumRetroUserDetailsAuthenticationProvider.authenticate(mockUsernamePasswordAuthenticationToken);
	}
	
	
	@Test
	public void shouldFireDisabledException(){
		expectedException.expect(DisabledException.class);
		expectedException.expectMessage(endsWith("User is disabled"));
		when(mockUserRepository.findByUserId("info@scrumretro.com")).thenReturn(createUser(false));
		scrumRetroUserDetailsAuthenticationProvider.authenticate(mockUsernamePasswordAuthenticationToken);
	}
	
	
	private User createUser(final Boolean isActive){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword("password");
		user.setActive(isActive);
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
}
