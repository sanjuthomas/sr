package com.scrumretro.worker;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrumretro.repository.ItemRepository;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Item;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.repository.model.User;
import com.scrumretro.web.model.ItemResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ItemWorker {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RetrospectiveRepository retrospectiveRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public void setRetrospectiveRepository(
			final RetrospectiveRepository retrospectiveRepository) {
		this.retrospectiveRepository = retrospectiveRepository;
	}

	public void setItemRepository(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	/**
	 * This method shall take an item id and construct ItemReponse object for UI.
	 * 
	 * @param id
	 * @return
	 */
	public ItemResponse findById(final String id){
		final Item item = itemRepository.findById(id);
		return createItemResponse(item);
	}
	
	public List<Item> findByRetrospectiveId(final String projectId){
		return null;
	}
	
	public List<Item> findByUserId(final String projectId){
		return null;
	}
	
	/**
	 * Load all the related documents and populate the UI model object.
	 * 
	 * @param item
	 * @return
	 */
	private ItemResponse createItemResponse(final Item item){
		
		final User user = userRepository.findByUserId(item.getUserId());
		final Retrospective retrospective = retrospectiveRepository.findById(item.getRetrospectiveId());
		final Project project = projectRepository.findById(retrospective.getProjectId());
		final ItemResponse itemResponse = new ItemResponse();
		BeanUtils.copyProperties(item, itemResponse);
		itemResponse.setOwnerDispalyName(user.getDisplayName());
		itemResponse.setItemTypeDisplayString(item.getItemType().getDisplayString());
		itemResponse.setProjectName(project.getName());
		itemResponse.setRetrospectiveName(retrospective.getName());
		return itemResponse;
	}
}
