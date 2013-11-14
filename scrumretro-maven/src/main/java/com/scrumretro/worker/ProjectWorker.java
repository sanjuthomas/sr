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
@Component
public class ProjectWorker {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public ProjectResponse findById(final String projectId){
		final Project project = projectRepository.findById(projectId);
		final ProjectResponse projectResponse = new ProjectResponse();
		BeanUtils.copyProperties(project, projectResponse);
		projectResponse.setOwnerDisplayName(project.getUser().getDisplayName());
		return projectResponse;
	}

}
