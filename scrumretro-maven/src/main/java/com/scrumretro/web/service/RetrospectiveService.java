package com.scrumretro.web.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.rest.Service;
import com.scrumretro.web.model.response.RetrospectiveResponse;
import com.scrumretro.worker.RetrospectiveWorker;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Controller
public class RetrospectiveService extends Service{
	
	private RetrospectiveWorker retrospectiveWorker;

	public void setRetrospectiveWorker(final RetrospectiveWorker retrospectiveWorker) {
		this.retrospectiveWorker = retrospectiveWorker;
	}

	/**
	 * This method shall find the Retrospective and c
	 * 
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/retrospective/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RetrospectiveResponse findById(@PathVariable("id") final String id){
		return retrospectiveWorker.findById(id);
	}
	
	
}
