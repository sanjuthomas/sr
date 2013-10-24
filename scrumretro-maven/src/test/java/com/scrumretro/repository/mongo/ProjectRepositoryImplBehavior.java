package com.scrumretro.repository.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.scrumretro.repository.mongo.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProjectRepositoryImplBehavior extends MongoBaseUnitTest{ 
	
	@Autowired 
	private ProjectRepositoryImpl projectRepository;

	@Test
	@ShouldMatchDataSet(location = "/testData/project/project-p1.json")
	public void shouldSaveOneProjectDocument(){
		projectRepository.insert(createProject());
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		project.setOrganization("o1");
		return project;
	}
}
