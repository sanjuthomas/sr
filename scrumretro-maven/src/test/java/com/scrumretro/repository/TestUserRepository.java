package com.scrumretro.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.mongodb.Mongo;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.test.BaseUnitTest;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestUserRepository extends BaseUnitTest{

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Before
	public void setUp(){
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/user/user-u1.json")
	public void shouldSaveUser() {
		final User user = createUser();
		user.setPassword("");
		userRepository.save(user);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldFindByUserId(){
		final User user = userRepository.findByUserId("info@scrumretro.com");
		assertNotNull(user);
		assertEquals("info@scrumretro.com", user.getUserId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u2.json"})
	public void shouldMatchPassword(){
		final User user = userRepository.findByUserId("info@scrumretro.com");
		assertNotNull(user);
		assertEquals("info@scrumretro.com", user.getUserId());
		assertTrue(bCryptPasswordEncoder.matches("password", user.getPassword()));
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldNotFindByUserId(){
		final User user = userRepository.findByUserId("inf@scrumretro.com");
		assertNull(user);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldFindUsersByOrganization(){
		final List<User> users = userRepository.findUsersByOrgranization("organization");
		assertNotNull(users);
		assertTrue(users.size() > 0);
		assertEquals("info@scrumretro.com", users.get(0).getUserId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldNotFindUsersByOrganization(){
		final List<User> users = userRepository.findUsersByOrgranization("organidddzation");
		assertEquals(0, users.size());
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		user.setPassword("password");
		user.setActive(true);
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
			return "com.scrumretro.*";
		}
	}
}
