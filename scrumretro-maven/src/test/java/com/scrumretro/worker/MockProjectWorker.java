package com.scrumretro.worker;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.web.model.ProjectResponse;

public class MockProjectWorker implements ProjectWorker {

	public ProjectResponse findById(final String projectId){
		final ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId("pid");
		projectResponse.setName("pname");
		projectResponse.setDescription("pdescription");
		projectResponse.setOwnerDisplayName("lastName, firstName");
		projectResponse.setOrganization("Organization");
		return projectResponse;
		
	}

	@Override
	public void setProjectRepository(ProjectRepository projectRepository) {
		// TODO Auto-generated method stub
		
	}
	
}
