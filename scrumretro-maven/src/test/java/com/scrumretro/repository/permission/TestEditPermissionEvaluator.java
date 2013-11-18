package com.scrumretro.repository.permission;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestEditPermissionEvaluator {
	
	private EditPermissionEvaluator editPermissionEvaluator;
	
	@Mock
	private Authentication mockAuthentication;
	
	@Before
	public void setUp(){
		initMocks(this);
		editPermissionEvaluator = new EditPermissionEvaluator();
		when(mockAuthentication.getName()).thenReturn("info@scrumretro.com");
	}

	@Test
	public void shouldHavePermission(){
		assertTrue(editPermissionEvaluator.hasPermission(mockAuthentication, createProject(), null));
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setId("pid");
		project.setName("pname");
		project.setDescription("pdescription");
		project.setUser(createUser());
		return project;
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword("password");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}
}
