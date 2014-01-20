package com.scrumretro.web.service;

import com.scrumretro.rest.Service;
import com.scrumretro.worker.RetrospectiveWorker;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class RetrospectiveService extends Service{
	
	private RetrospectiveWorker retrospectiveWorker;

	public void setRetrospectiveWorker(final RetrospectiveWorker retrospectiveWorker) {
		this.retrospectiveWorker = retrospectiveWorker;
	}

}
