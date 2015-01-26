package com.scrumretro.worker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;
import com.scrumretro.security.util.SecurityContextUtil;
import com.scrumretro.web.model.request.ProjectRequest;
import com.scrumretro.web.model.response.ProjectResponse;

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
	
	@Autowired
	private SecurityContextUtil securityContextUtil;
	
	public void setSecurityContextUtil(final SecurityContextUtil securityContextUtil) {
		this.securityContextUtil = securityContextUtil;
	}

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
	 * This method shall take a project object and save it to db.
	 * 
	 * @param project
	 * @return
	 */
	public ProjectResponse save(final ProjectRequest projectRequest) {
		return createProjectResponse(projectRepository.save(createProject(projectRequest)));
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public List<ProjectResponse> findByUserId(String username) {
		final List<Project> projects = projectRepository.findByOwner(username);
		final List<ProjectResponse> projectResponses = new ArrayList<ProjectResponse>();
		for(final Project project : projects){
			projectResponses.add(createProjectResponse(project));
		}
		return projectResponses;
	}
	
	
	private Project createProject(final ProjectRequest projectRequest){
		final Project project = new Project();
		BeanUtils.copyProperties(projectRequest, project);
		project.setOwner(securityContextUtil.getUserProfile().getUsername()); 
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
