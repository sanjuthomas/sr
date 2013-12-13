package com.scrumretro.web.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	
	
		
}
