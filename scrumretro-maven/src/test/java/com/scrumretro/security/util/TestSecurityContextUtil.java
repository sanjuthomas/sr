package com.scrumretro.security.util;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrumretro.security.authentication.ScrumRetroUser;

/**
 * @author Ragil 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class TestSecurityContextUtil {

	@Autowired
    private SecurityContextUtil securityContextUtil;
	
	@Mock
	private SecurityContext mockSecurityContext;
	
	@Mock
	private Authentication mockAuthentication;
	
	@Mock
	private ScrumRetroUser mockScrumRetroUser;

    @Before
    public void setUp() {
    	initMocks(this);
    	when(mockAuthentication.getPrincipal()).thenReturn(mockScrumRetroUser);
    	when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
    	SecurityContextHolder.setContext(mockSecurityContext);
    }

    
    @Test
    public void shouldReturnCurrentUserProfile(){
    	assertEquals(mockScrumRetroUser, securityContextUtil.getUserProfile());
    }
    
    @Test
    public void shouldReturnUnknownUserProfile(){
    	SecurityContextHolder.clearContext();
    	assertEquals(ScrumRetroUser.UNKNOWN_USER, securityContextUtil.getUserProfile());
    }
  
}
