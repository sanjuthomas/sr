package com.scrumretro.repository.audit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.security.util.SecurityContextUtil;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class TestScrumRetroAuditor {

	@Mock
	private SecurityContextUtil securityContextUtil;
	
	@Mock
	private ScrumRetroUser scrumRetroUser;
	
	@Autowired
	private ScrumRetroAuditor scrumRetroAuditor;
	
	@Before
	public void setUp(){
		initMocks(this);
		when(scrumRetroUser.getUsername()).thenReturn("testuser@scrumretro.com");
		when(securityContextUtil.getUserProfile()).thenReturn(scrumRetroUser);
		scrumRetroAuditor.setSecurityContextUtil(securityContextUtil);
	}
	
	@Test
	public void shoulGetCurrentAuditor(){
		assertEquals("testuser@scrumretro.com", scrumRetroAuditor.getCurrentAuditor());
	}
}
