package com.scrumretro.worker;

import org.springframework.beans.factory.annotation.Autowired;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.web.model.ProjectResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ProjectWorker {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public ProjectResponse findById(final String projectId){
		return null;
	}
	
	

}
