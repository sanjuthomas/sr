package com.scrumretro.web.service;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.scrumretro.rest.Response;
import com.scrumretro.test.IntegrationTest;

/**
 * Integration test cases for ProjectService.
 *  
 * This testcase would wire ProjectService, ProjectWorker and ProjectRepository.
 * 
 * @author Sanju Thomas
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ProjectServiceIntegrationTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("scrumretro-test");

	@Autowired
	private ProjectService projectService;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectService).build();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p2.json"})
	public void shouldFindProjectById() throws Exception{
		mockMvc.perform(get("/project/findById/{id}", "2fasdf123333"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(Response.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$id", is("2fasdf123333")))
		.andExpect(jsonPath("$name", is("p2")))
		.andExpect(jsonPath("$description", is("This is a test project called p2")))
		.andExpect(jsonPath("$organization", is("organization")))
		.andExpect(jsonPath("$ownerDisplayName", is("lastName, firstName")));
	}
}
