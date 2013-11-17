package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.web.model.ProjectResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface ProjectWorker {
	
	
	
	public void setProjectRepository(final ProjectRepository projectRepository) ;
		
	
	public ProjectResponse findById(final String projectId);
		

}

