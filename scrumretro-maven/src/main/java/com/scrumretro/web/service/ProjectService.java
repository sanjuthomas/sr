package com.scrumretro.web.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.maven.wagon.authorization.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.web.model.ErrorResponse;
import com.scrumretro.web.model.ProjectResponse;
import com.scrumretro.worker.ProjectWorker;

/**
 * This class encapsulate all the service methods associate with Project
 * document.
 * 
 * @author Sanju Thomas
 * 
 */
@Controller
public class ProjectService {

	@Autowired
	private ProjectWorker projectWorker;

	public void setProjectWorker(final ProjectWorker projectWorker) {
		this.projectWorker = projectWorker;
	}

	/**
	 * This method shall take project id request parameter and return the
	 * ProjectResponse in json format.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/project/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectResponse findById(@PathVariable("id") final String id) {
		return projectWorker.findById(id);
	}
	
	/**
	 * This method will get called any of the request mapping is not handling AuthorizationException
	 * ErrorResponse in json format.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public ErrorResponse handleIOException(AuthorizationException ex, HttpServletRequest request) {
		// Error code we need change 
		return new ErrorResponse("100", ex.getMessage());
	    
	  }

}
