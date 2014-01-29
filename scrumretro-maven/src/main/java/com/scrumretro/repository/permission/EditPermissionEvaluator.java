package com.scrumretro.repository.permission;

import java.io.Serializable;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class EditPermissionEvaluator implements PermissionEvaluator{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public boolean hasPermission(final Authentication authentication, final Object targetDomainObject, final Object permission) {
		final BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(targetDomainObject);
		final String projectId = (String) beanWrapper.getPropertyValue("id");
		final Project project = (Project) projectRepository.findById(projectId);
		return authentication.getName().equals(project.getOwner());
	}

	@Override
	public boolean hasPermission(final Authentication arg0, final Serializable arg1, final String arg2, final Object arg3) {
		return false;
	}
}
