package com.scrumretro.repository.permission;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestEditPermissionEvaluator {
	
	private EditPermissionEvaluator editPermissionEvaluator;
	
	@Mock
	private Authentication mockAuthentication;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Before
	public void setUp(){
		initMocks(this);
		editPermissionEvaluator = new EditPermissionEvaluator();
		when(mockAuthentication.getName()).thenReturn("testuser@scrumretro.com");
		when(projectRepository.findById(any(String.class))).thenReturn(createProject());
		editPermissionEvaluator.setProjectRepository(projectRepository);
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
		project.setOwner("testuser@scrumretro.com");
		return project;
	}
}
