package com.scrumretro.repository;

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
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RetrospectiveBehavior {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private RetrospectiveRepository retrospectiveRepository;
	
	@Before
	public void setUp(){
		this.retrospectiveRepository.deleteAll();
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/retrospective/retrospective-r1.json")
	public void shouldSaveRetrospective(){
		retrospectiveRepository.save(createRetrospective());
	}
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setName("retrospective-r1");
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		retrospective.setUser(user);
		final Project project = new Project();
		project.setId("5270269044ae1440f787333a");
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		project.setOrganization("o1");
		retrospective.setProject(project);
		return retrospective;
	}
	
	
	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { RetrospectiveRepository.class })
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
