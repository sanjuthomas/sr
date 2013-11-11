package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.model.ProjectResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestProjectWorker {
	
	private ProjectWorker projectWorker;
	
	@Mock
	private ProjectRepository mockProjectRepository;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		final Project project = createProject();
		when(mockProjectRepository.findById(any(String.class))).thenReturn(project);
		projectWorker = new ProjectWorker();
		projectWorker.setProjectRepository(mockProjectRepository);
	}
	
	@Test
	public void shouldFindById(){
		ProjectResponse projectResponse = projectWorker.findById("pid");
		assertNotNull(projectResponse);
		assertEquals("pid", projectResponse.getId());
		assertEquals("pname", projectResponse.getName());
		assertEquals("pdescription", projectResponse.getDescription());
		assertEquals("lastName, firstName", projectResponse.getOwnerDisplayName());
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
