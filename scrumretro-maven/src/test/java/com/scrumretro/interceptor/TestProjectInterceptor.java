package com.scrumretro.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



import java.lang.reflect.InvocationTargetException;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.BeanUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrumretro.exception.AuthorizationException;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.util.UserThreadLocal;
import com.scrumretro.web.model.ProjectResponse;
import com.scrumretro.web.model.UserResponse;
import com.scrumretro.worker.ProjectWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestProjectInterceptor {

	@Autowired
	private ProjectWorker projectWorker;
	
	@Mock
	private ProjectRepository mockProjectRepository;
	
	
	@Before
	public void setUp(){
		//initMocks(this);
		//final Project project = createProject();
		//when(mockProjectRepository.findById(any(String.class))).thenReturn(project);
		//projectWorker = new ProjectWorker();
		//projectWorker.setProjectRepository(mockProjectRepository);
		
		
	}
	
	@Test
	public void shouldFindById(){
	    UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser(),userResponse);
		UserThreadLocal.set(userResponse);
		ProjectResponse projectResponse = projectWorker.findById("pid");
		assertNotNull(projectResponse);
		assertEquals("pid", projectResponse.getId());
		assertEquals("pname", projectResponse.getName());
		assertEquals("pdescription", projectResponse.getDescription());
		assertEquals("lastName, firstName", projectResponse.getOwnerDisplayName());
	}
	
	@Test
	public void shouldThrowError(){
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createNotAuthorizedUser(),userResponse);
		UserThreadLocal.set(userResponse);
		boolean isNotAuthorized = false;
		try{
		ProjectResponse projectResponse = projectWorker.findById("pid");
		}catch(AuthorizationException e){
			isNotAuthorized = true;
		}
		assertTrue(isNotAuthorized);
		
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

	
	private User createNotAuthorizedUser(){
		final User user = new User();
		user.setUserId("notAuth@scrumretro.com");
		user.setPassword("password");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName1");
		userDetail.setLastName("lastName1");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}

}
