package com.scrumretro.interceptor;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.scrumretro.exception.AuthorizationException;
import com.scrumretro.util.UserThreadLocal;
import com.scrumretro.web.model.ProjectResponse;
import com.scrumretro.web.model.UserResponse;

@Aspect
public class ProjectInterceptor {

	@Pointcut("execution(* com.scrumretro.worker.ProjectWorker.findById(..))")
	private void findById() {
	}

	
	 @Before("execution(* com.scrumretro.worker.ProjectWorker.findById(..)) &&" +
	 "args(id)") public void beforeSave(String id) {
	  
	  }
	

	@AfterReturning(pointcut = "findById()", returning = "projectResponse")
	public void viewProject(ProjectResponse projectResponse) {
		// time being checking project owner display name and current user disp name is same. We need to chnage to real implementation 
		UserResponse userResponse = UserThreadLocal.get();
		if(!projectResponse.getOwnerDisplayName().equals(userResponse.getDisplayName())){
			throw new AuthorizationException("Not Authorized to view the project");
		}
		
		
	}

}
