package com.scrumretro.repository.mongo;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.repository.mongo.model.Project;

/**
 * 
 * @author Sanju Thomas
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProjectRepositoryImplBehavior {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private ProjectRepositoryImpl projectRepository;

	@Before
	public void setUp() {
		mongoTemplate.dropCollection("project");
	}

	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1.json")
	public void shouldSaveOneProjectDocument() {
		projectRepository.insert(createProject("p1", "o1"));
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1andp2.json")
	public void shouldSaveTwoProjectDocument() {
		projectRepository.insert(createProject("p1", "o1"));
		projectRepository.insert(createProject("p2", "o2"));
	}
	
	

	private Project createProject(final String name, final String organization) {
		final Project project = new Project();
		project.setName(name);
		project.setDescription("This is a test project called " + name);
		project.setOrganization(organization);
		return project;
	}

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { ProjectRepositoryImpl.class })
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
