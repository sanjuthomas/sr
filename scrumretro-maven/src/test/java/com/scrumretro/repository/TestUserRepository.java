package com.scrumretro.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.test.BaseUnitTest;
import com.scrumretro.util.EncryptionUtil;

/**
 * 
 * @author Sanju Thomas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestUserRepository extends BaseUnitTest{

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	@ShouldMatchDataSet(location = "/testData/user/user-u1.json")
	public void shouldSaveUser() {
		User user = createUser();
		user.setPassword(EncryptionUtil.encryptPassword(user.getPassword()));
		userRepository.save(user);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldFindByUserIdAndPassword(){
		User user = userRepository.findByUserIdAndPassword("info@scrumretro.com", EncryptionUtil.encryptPassword("password"));
		assertNotNull(user);
		assertEquals("info@scrumretro.com", user.getUserId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldNotFindByUserIdAndPassword(){
		User user = userRepository.findByUserIdAndPassword("info@scrumretro.com", EncryptionUtil.encryptPassword("test"));
		assertNull(user);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldFindByUserId(){
		User user = userRepository.findByUserId("info@scrumretro.com");
		assertNotNull(user);
		assertEquals("info@scrumretro.com", user.getUserId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldNotFindByUserId(){
		User user = userRepository.findByUserId("inf@scrumretro.com");
		assertNull(user);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldFindUsersByOrganization(){
		List<User> users = userRepository.findUsersByOrgranization("organization");
		assertNotNull(users);
		assertTrue(users.size() > 0);
		assertEquals("info@scrumretro.com", users.get(0).getUserId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/user/user-u1.json"})
	public void shouldNotFindUsersByOrganization(){
		List<User> users = userRepository.findUsersByOrgranization("organidddzation");
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

}
