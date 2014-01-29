package com.scrumretro.web.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.repository.model.Project;
import com.scrumretro.rest.Response;
import com.scrumretro.web.model.ProjectRequest;
import com.scrumretro.web.model.ProjectResponse;
import com.scrumretro.worker.ProjectWorker;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-root-context.xml", "classpath:test-servlet-context.xml" })
public class TestProjectService {

	@Autowired
	private ApplicationContext applicationContext;

	private MockMvc mockMvc;

	@Mock
	private ProjectWorker projectWorker;

	@Autowired
	private ProjectService projectService;

	@Captor
	private ArgumentCaptor<Project> projectCaptor;

	@Before
	public void setUp() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectService).build();
		when(projectWorker.findById(any(String.class))).thenReturn(
				createProjectResponse());
		when(projectWorker.save(any(ProjectRequest.class))).thenReturn(
				createProjectResponse());
		projectService.setProjectWorker(projectWorker);
	}

	@Test
	public void shouldFindByProjectId() throws Exception {
		mockMvc.perform(get("/project/findById/{id}", "p1"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(Response.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$id", is("p1")))
				.andExpect(jsonPath("$name", is("pname")))
				.andExpect(jsonPath("$description", is("pdescription")))
				.andExpect(jsonPath("$organization", is("o1")))
				.andExpect(
						jsonPath("$ownerDisplayName", is("lastName, firstName")));
	}

	@Test
	public void shouldSaveProject() throws Exception {
		this.mockMvc.perform(
				post("/project/save/").content(createProjectRequest().toString())
						.contentType(Response.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$id", is("p1")))
				.andExpect(jsonPath("$name", is("pname")))
				.andExpect(jsonPath("$description", is("pdescription")))
				.andExpect(jsonPath("$organization", is("o1")))
				.andExpect(
						jsonPath("$ownerDisplayName", is("lastName, firstName")));
	}

	private ProjectResponse createProjectResponse() {
		final ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId("p1");
		projectResponse.setName("pname");
		projectResponse.setDescription("pdescription");
		projectResponse.setOrganization("o1");
		projectResponse.setOwnerDisplayName("lastName, firstName");
		return projectResponse;
	}

	private ProjectRequest createProjectRequest() {
		final ProjectRequest projectRequest = new ProjectRequest();
		projectRequest.setId("pid");
		projectRequest.setName("pname");
		projectRequest.setDescription("pdescription");
		return projectRequest;
	}
}
