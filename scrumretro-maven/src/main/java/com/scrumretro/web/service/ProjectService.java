package com.scrumretro.web.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.web.model.ProjectRequest;
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
	 * This service shall take project id request parameter and return the
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
	 * This service shall take project json as request and save or update the given project.
	 * This method shall return ProjectResponse in json format.
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/project/save/", method=RequestMethod.POST)
	@ResponseBody
	public ProjectResponse save(@Valid @RequestBody final ProjectRequest projectRequest){
		return projectWorker.save(projectRequest);
	}
	
}
