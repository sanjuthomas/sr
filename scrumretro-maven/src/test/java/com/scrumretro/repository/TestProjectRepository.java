package com.scrumretro.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
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

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestProjectRepository {

	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private ProjectRepository projectRepository;
	

//	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1.json")
	public void shouldSaveProject() {
		projectRepository.save(createProject());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByName(){
		final Project project = projectRepository.findByName("p1");
		assertNotNull(project);
		assertEquals("p1", project.getName());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByUserId(){
		final List<Project> projects = projectRepository.findByUserId("info@scrumretro.com");
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
		assertEquals("p1", projects.get(0).getName());
	}
	

	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByUser(){
		final List<Project> projects = projectRepository.findByUser(createUser());
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
		assertEquals("p1", projects.get(0).getName());
	}
	
	private Project createProject() {
		final Project project = new Project();
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		project.setOrganization("o1");
		final User user = createUser();
		project.setUser(user);
		return project;
	}
	
	private User createUser(){
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { ProjectRepository.class })
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
