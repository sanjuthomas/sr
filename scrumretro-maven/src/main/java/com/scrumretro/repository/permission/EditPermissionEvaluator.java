package com.scrumretro.repository.permission;

import java.io.Serializable;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.scrumretro.repository.model.User;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class EditPermissionEvaluator implements PermissionEvaluator{

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		final BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(targetDomainObject);
		final User user = (User) beanWrapper.getPropertyValue("user");
		return authentication.getName().equals(user.getUserId());
	}

	@Override
	public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
		return false;
	}
}
