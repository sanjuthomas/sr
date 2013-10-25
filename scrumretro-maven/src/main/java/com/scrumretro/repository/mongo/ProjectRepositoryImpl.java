package com.scrumretro.repository.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.mongo.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Repository
public class ProjectRepositoryImpl implements ProjectRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	public void insert(final Project project) {
		mongoTemplate.insert(project);
	}
	
}
