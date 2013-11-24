package com.scrumretro.security.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

public class TestScrumretroUserService {

	private ScrumRetroUserDetailsService scrumretroUserService;
	
	@Mock
	private UserRepository mockUserRepository;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		final User user = createUser();
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(user);
		scrumretroUserService = new ScrumRetroUserDetailsService();
		scrumretroUserService.setUserRepository(mockUserRepository);
	}
	
	@Test
	public void shouldLoadUserByUsername(){
		final ScrumRetroUser scrumretroUser = (ScrumRetroUser)scrumretroUserService.loadUserByUsername("info@scrumretro.com");
		assertNotNull(scrumretroUser);
		assertEquals("info@scrumretro.com", scrumretroUser.getUsername());
		assertEquals("password", scrumretroUser.getPassword());
		final Collection<GrantedAuthority> gaCol = scrumretroUser.getAuthorities();
		assertNotNull(gaCol);
		assertTrue(gaCol.size() > 0);
		//final GrantedAuthority ga = ((List<GrantedAuthority>)gaCol).get(0);
		final Iterator<GrantedAuthority> it = gaCol.iterator();
		final String roles [] = {"ROLE_USER"};
		while(it.hasNext()){
			assertTrue(contains(roles, it.next().getAuthority()));
		}
		
	}
	
	private Boolean contains(final String strArray [],final String str){
		for(int i=0;i<strArray.length;i++){
			
			if(str.equals(strArray[i])){
				return true;
			}
		}
		return false;
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
