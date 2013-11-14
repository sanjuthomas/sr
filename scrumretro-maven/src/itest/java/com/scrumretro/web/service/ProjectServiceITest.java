package com.scrumretro.web.service;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.worker.ProjectWorker;

/**
 * Integration test cases for ProjectService. 
 * This testcase would wire ProjectService, ProjectWorker and ProjectRepository.
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProjectServiceITest {

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
		.andExpect(status().isOk());
	}
	
	
	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { ProjectRepository.class, ProjectService.class, ProjectWorker.class })
	@PropertySource("classpath:application.properties")
	static class MongoConfiguration extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "scrumretro-test";
		}

		@Override
		public Mongo mongo() {
			return new Fongo("mongo-test").getMongo();
		}

		@Override
		protected String getMappingBasePackage() {
			return "com.scrumretro.repository.mongo";
		}
		
	}
}
