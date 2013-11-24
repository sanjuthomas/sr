package com.scrumretro.security.authentication;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.repository.UserRepository;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestScrumRetroUserDetailsAuthenticationProvider {
	
	@Autowired
	private ScrumRetroUserDetailsAuthenticationProvider scrumRetroUserDetailsAuthenticationProvider;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	
	public void shouldAuthenticate(){
		
	}
	
	public void shouldNotAuthenticate(){
		
	}
}
