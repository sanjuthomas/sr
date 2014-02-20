package com.scrumretro.worker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrumretro.enums.RetrospectiveStatus;
import com.scrumretro.exception.AuthorizationException;
import com.scrumretro.exception.ScrumRetroRuntimeException;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.security.util.SecurityContextUtil;
import com.scrumretro.web.model.RetrospectiveRequest;
import com.scrumretro.web.model.RetrospectiveResponse;
import com.scrumretro.web.util.NotificationUtil;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Component
public class RetrospectiveWorker {
	
	@Autowired
	private RetrospectiveRepository retrospectiveRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private SecurityContextUtil securityContextUtil;
	
	@Autowired
	private NotificationUtil notificationUtil;

	public void setRetrospectiveRepository(
			final RetrospectiveRepository retrospectiveRepository) {
		this.retrospectiveRepository = retrospectiveRepository;
	}
	
	
	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}


	public void setSecurityContextUtil(SecurityContextUtil securityContextUtil) {
		this.securityContextUtil = securityContextUtil;
	}


	public void setNotificationUtil(NotificationUtil notificationUtil) {
		this.notificationUtil = notificationUtil;
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
		retrospectiveResponse.setStatusDisplayString(retrospective.getRetrospectiveStatus()==null?null:retrospective.getRetrospectiveStatus().getDisplayString());
		return retrospectiveResponse;
	}
	
	 /** This method shall take id and update the open for vote status into mongodb.
	 * 
	 * @param id
	 * @return
	 */
	public RetrospectiveResponse openForVote(final String id){
	   Retrospective retrospective = retrospectiveRepository.findById(id);
	   final Project project = projectRepository.findById(retrospective.getId());
	   if(!project.getOwner().equals(securityContextUtil.getUserProfile().getUsername())){
		   throw new ScrumRetroRuntimeException(new AuthorizationException());
	   }
	   retrospective.setRetrospectiveStatus(RetrospectiveStatus.OPEN_FOR_VOTING);
	   //retrospective = 
	   retrospectiveRepository.save(retrospective);
	   notificationUtil.notifyOpenForVote(project, retrospective);
	   return createRetrospectiveResponse(retrospective);
	}
	
	 /** This method shall take id and update the close status into mongodb.
		 * 
		 * @param id
		 * @return
		 */
	public RetrospectiveResponse close(final String id){
	   Retrospective retrospective = retrospectiveRepository.findById(id);
	   final Project project = projectRepository.findById(retrospective.getId());
	   if(!project.getOwner().equals(securityContextUtil.getUserProfile().getUsername())){
		   throw new ScrumRetroRuntimeException(new AuthorizationException());
	   }
	   retrospective.setRetrospectiveStatus(RetrospectiveStatus.CLOSED);
	   //retrospective = 
	   retrospectiveRepository.save(retrospective);
	   return createRetrospectiveResponse(retrospective);
		}

}
