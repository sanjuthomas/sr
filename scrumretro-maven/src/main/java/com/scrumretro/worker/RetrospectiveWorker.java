package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.web.model.RetrospectiveRequest;
import com.scrumretro.web.model.RetrospectiveResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Component
public class RetrospectiveWorker {
	
	@Autowired
	private RetrospectiveRepository retrospectiveRepository;

	public void setRetrospectiveRepository(
			final RetrospectiveRepository retrospectiveRepository) {
		this.retrospectiveRepository = retrospectiveRepository;
	}
	
	
	/**
	 * This method shall find the Retrospective object for the given id and 
	 * convert it to RetrospectiveResponse;
	 * 
	 * @param id
	 * @return
	 */
	public RetrospectiveResponse findById(final String id){
		return createRetrospectiveResponse(retrospectiveRepository.findById(id));
	}
	
	/**
	 * This method shall take RetrospectiveRequest and save it into mongodb.
	 * 
	 * @param retrospectiveRequest
	 * @return
	 */
	public RetrospectiveResponse save(final RetrospectiveRequest retrospectiveRequest){
		final Retrospective retrospective = new Retrospective();
		BeanUtils.copyProperties(retrospectiveRequest, retrospective);
		return createRetrospectiveResponse(retrospectiveRepository.save(retrospective));
	}
	
	private RetrospectiveResponse createRetrospectiveResponse(final Retrospective retrospective){
		final RetrospectiveResponse retrospectiveResponse = new RetrospectiveResponse();
		BeanUtils.copyProperties(retrospective, retrospectiveResponse);
		return retrospectiveResponse;
	}

}
