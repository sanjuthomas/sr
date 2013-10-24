package com.scrumretro.repository.mongo;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

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
	private ProjectRepositoryImpl projectRepository;

	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1.json")
	public void shouldSaveOneProjectDocument() {
		projectRepository.insert(createProject());
	}

	private Project createProject() {
		final Project project = new Project();
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		project.setOrganization("o1");
		return project;
	}

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { ProjectRepositoryImpl.class })
	@PropertySource("classpath:application.properties")
	static class MongoBaseUnitTest extends AbstractMongoConfiguration {

		@Rule
		public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
				"scrumretro-test");

		@Autowired
		private ApplicationContext applicationContext;

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
