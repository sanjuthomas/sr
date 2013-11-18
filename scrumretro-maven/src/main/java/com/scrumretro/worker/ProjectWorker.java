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
public class ProjectWorker{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	/**
	 * This method shall take project id and find it from db.
	 * 
	 * @param projectId
	 * @return
	 */
	public ProjectResponse findById(final String projectId){
		return createProjectResponse(projectRepository.findById(projectId));
	}

	/**
	 * This method shall take a project object and save it to db.
	 * 
	 * @param project
	 * @return
	 */
	public ProjectResponse save(final Project project) {
		return createProjectResponse(projectRepository.save(project));
	}
	
	private ProjectResponse createProjectResponse(final Project project){
		final ProjectResponse projectResponse = new ProjectResponse();
		BeanUtils.copyProperties(project, projectResponse);
		projectResponse.setOwnerDisplayName(project.getUser().getDisplayName());
		projectResponse.setOrganization(project.getUser().getUserDetail().getOrganization());
		return projectResponse;
	}

}
