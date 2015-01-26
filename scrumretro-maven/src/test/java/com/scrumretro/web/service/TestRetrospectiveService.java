package com.scrumretro.web.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.rest.Response;
import com.scrumretro.web.model.response.RetrospectiveResponse;
import com.scrumretro.worker.RetrospectiveWorker;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-root-context.xml", "classpath:test-servlet-context.xml" })
public class TestRetrospectiveService {
	
	private MockMvc mockMvc;
	
	@Autowired
	private RetrospectiveService retrospectiveService;
	
	@Mock
	private RetrospectiveWorker mockRetrospectiveWorker;
	
	
	@Before
	public void setUp(){
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(retrospectiveService).build();
		this.retrospectiveService.setRetrospectiveWorker(mockRetrospectiveWorker);
		when(mockRetrospectiveWorker.findById(any(String.class))).thenReturn(createRetrospectiveResponse());
	}
	
	@Test
	public void shouldFindById() throws Exception{
		mockMvc.perform(get("/retrospective/findById/{id}", "r1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(Response.APPLICATION_JSON_UTF8));
	}
	
	public RetrospectiveResponse createRetrospectiveResponse(){
		final RetrospectiveResponse retrospectiveResponse = new RetrospectiveResponse();
		retrospectiveResponse.setId("r1");
		retrospectiveResponse.setName("rname");
		retrospectiveResponse.setProjectId("p1");
		return retrospectiveResponse;
	}
}
