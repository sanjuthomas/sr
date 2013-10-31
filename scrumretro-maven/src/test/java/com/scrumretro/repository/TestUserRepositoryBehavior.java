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
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestUserRepositoryBehavior {

	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp(){
		this.userRepository.deleteAll();
	}

	@Test
	@ShouldMatchDataSet(location = "/testData/user/user-u1.json")
	public void shouldSaveUser() {
		userRepository.save(createUser());
	}
	
	private User createUser(){
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		user.setPassword("password");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { UserRepository.class })
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