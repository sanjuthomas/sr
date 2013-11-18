package com.scrumretro.web.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.rest.Response;
import com.scrumretro.web.model.ProjectResponse;
import com.scrumretro.worker.ProjectWorker;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestProjectService {
	
	private MockMvc mockMvc;
	
	@Mock
	private ProjectWorker projectWorker;
	
	private ProjectService projectService;
	
	@Before
	public void setUp(){
		initMocks(this);
		projectService = new ProjectService();
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectService).build();
		when(projectWorker.findById(any(String.class))).thenReturn(createProjectResponse());
		projectService.setProjectWorker(projectWorker);
	}
	
	@Test
	public void shouldFindByProjectId() throws Exception{
		mockMvc.perform(get("/project/findById/{id}", "p1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(Response.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$id", is("p1")))
		.andExpect(jsonPath("$name", is("pname")))
		.andExpect(jsonPath("$description", is("pdescription")))
		.andExpect(jsonPath("$organization", is("o1")))
		.andExpect(jsonPath("$ownerDisplayName", is("lastName, firstName")));
	}
	
	@Test
	public void shouldSaveProject(){
	}
	
	
	private ProjectResponse createProjectResponse(){
		final ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId("p1");
		projectResponse.setName("pname");
		projectResponse.setDescription("pdescription");
		projectResponse.setOrganization("o1");
		projectResponse.setOwnerDisplayName("lastName, firstName");
		return projectResponse;
	}
	
}
