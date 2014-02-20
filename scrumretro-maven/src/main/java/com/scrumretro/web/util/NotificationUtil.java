package com.scrumretro.web.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.scrumretro.enums.MessageType;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.rest.Message;
import com.scrumretro.web.servlet.WebSocket;

/**
 * 
 * @author Ragil
 *
 */
public class NotificationUtil {

	/**
	 * This method shall take a retrospective and project, and send the open for vote notification to all 
	 * online members of the project  .
	 * 
	 * @param project,retrospective
	 * @return
	 */
	public List<String> notifyOpenForVote(final Project project,final Retrospective retrospective){
		String message = getOpenForVoteMessage(project,retrospective);
		final List<String> members = project.getMembers();
		final List<String> onlineMembers = project.getMembers();
		final Map<String, WebSocket> connections = WebSocket.getConnections();  // will contains all active connects 
		for(final String userName : members){
			if(connections.containsKey(userName)){
				try {
					connections.get(userName).broadcast(message);  // here one connection is one connected user 
					onlineMembers.add(userName);
				} catch (IOException e) {
					// handling exception to make sure that it will iterate for all user. We can ignore if particular user connection failed.
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		return onlineMembers;
	}
	
	public String getOpenForVoteMessage(final Project project,final Retrospective retrospective){
		final Message message = new Message();
		message.setMessageType(MessageType.VOTING_BEGINS);
		//message.setProjectId(project.getId());
		//message.setRetrospectiveId(retrospective.getId());
		return message.toString();
	}
}
