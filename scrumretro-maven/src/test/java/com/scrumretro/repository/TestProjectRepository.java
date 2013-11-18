package com.scrumretro.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.test.BaseUnitTest;

/**
 * 
 * @author Sanju Thomas
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestProjectRepository extends BaseUnitTest{

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ProjectRepository projectRepository;
	
	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1.json")
	public void shouldSaveProject() {
		projectRepository.deleteAll();
		projectRepository.save(createProject());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p2.json"})
	public void shouldFindById(){
		final Project project = projectRepository.findById("2fasdf123333");
		assertNotNull(project);
		assertEquals("2fasdf123333", project.getId());
		assertEquals("p2", project.getName());
		projectRepository.deleteAll();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p2.json"})
	public void shouldNotFindById(){
		final Project project = projectRepository.findById("2df123333");
		assertNull(project);
		projectRepository.deleteAll();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByName(){
		final Project project = projectRepository.findByName("p1");
		assertNotNull(project);
		assertEquals("p1", project.getName());
		projectRepository.deleteAll();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldNotFindByName(){
		final Project project = projectRepository.findByName("p41");
		assertNull(project);
		projectRepository.deleteAll();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByUserId(){
		final List<Project> projects = projectRepository.findByUserId("info@scrumretro.com");
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
		assertEquals("p1", projects.get(0).getName());
		projectRepository.deleteAll();
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldNotFindByUserId(){
		final List<Project> projects = projectRepository.findByUserId("inf@scrumretro.com");
		assertEquals(0, projects.size());
		projectRepository.deleteAll();
	}
	

	@Test
	@UsingDataSet(locations = {"/testData/project/project-p1.json"})
	public void shouldFindByUser(){
		final List<Project> projects = projectRepository.findByUser(createUser());
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
		assertEquals("p1", projects.get(0).getName());
		projectRepository.deleteAll();
	}
	
	private Project createProject() {
		final Project project = new Project();
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		final User user = createUser();
		project.setUser(user);
		return project;
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		return user;
	}

}
