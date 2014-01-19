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
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.security.util.SecurityContextUtil;
import com.scrumretro.web.model.ProjectRequest;
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
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock 
	private SecurityContextUtil mockSecurityContextUtil;
	
	@Mock
	private ScrumRetroUser mockScrumRetroUser;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		final Project project = createProject();
		when(mockProjectRepository.findById(any(String.class))).thenReturn(project);
		when(mockProjectRepository.save(any(Project.class))).thenReturn(project);
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(createUser());
		when(mockScrumRetroUser.getUsername()).thenReturn("info@scrumretro.com");
		when(mockSecurityContextUtil.getUserProfile()).thenReturn(mockScrumRetroUser);
		projectWorker = new ProjectWorker();
		projectWorker.setProjectRepository(mockProjectRepository);
		projectWorker.setUserRepository(mockUserRepository);
		projectWorker.setSecurityContextUtil(mockSecurityContextUtil);
	}
	
	@Test
	public void shouldFindById(){
		final ProjectResponse projectResponse = projectWorker.findById("pid");
		validateProjectResponse(projectResponse);
	}
	
	@Test
	public void shouldSaveProject(){
		final ProjectResponse projectResponse = projectWorker.save(createProjectRequest());
		validateProjectResponse(projectResponse);
	}
	
	private void validateProjectResponse(final ProjectResponse projectResponse){
		assertNotNull(projectResponse);
		assertEquals("pid", projectResponse.getId());
		assertEquals("pname", projectResponse.getName());
		assertEquals("pdescription", projectResponse.getDescription());
		assertEquals("lastName, firstName", projectResponse.getOwnerDisplayName());
	}
	
	private ProjectRequest createProjectRequest(){
		final ProjectRequest projectRequest = new ProjectRequest();
		projectRequest.setId("pid");
		projectRequest.setName("pname");
		projectRequest.setDescription("pdescription");
		return projectRequest;
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setId("pid");
		project.setName("pname");
		project.setDescription("pdescription");
		project.setOwner("testuser@scrumretro.com");
		return project;
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
