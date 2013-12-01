package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.web.model.ProjectRequest;
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
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
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
	 * 	@Todo we have to wire the security context. possibly passed in from controller.
	 *  controller should be kept clean, the only job of controller shall be used only for delegate.
	 *  see the possibility of using HandlerMethodArgumentResolver, this can be done only 
	 *  after the authentication is done.
	 * 
	 * This method shall take a project object and save it to db.
	 * 
	 * @param project
	 * @return
	 */
	public ProjectResponse save(final ProjectRequest projectRequest) {
		return createProjectResponse(projectRepository.save(createProject(projectRequest)));
	}
	
	
	private Project createProject(final ProjectRequest projectRequest){
		final Project project = new Project();
		BeanUtils.copyProperties(projectRequest, project);
		project.setOwner("testuser@scrumretro.com"); // grab it from authentication object when it's available.
		return project;
	}
	
	private ProjectResponse createProjectResponse(final Project project){
		final ProjectResponse projectResponse = new ProjectResponse();
		BeanUtils.copyProperties(project, projectResponse);
		final User user = userRepository.findByUserId(project.getOwner());
		projectResponse.setOwnerDisplayName(user.getDisplayName());
		projectResponse.setOrganization(user.getUserDetail().getOrganization());
		return projectResponse;
	}
}
