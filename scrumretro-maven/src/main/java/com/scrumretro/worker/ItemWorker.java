package com.scrumretro.worker;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrumretro.repository.ItemRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Item;
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

	public ItemResponse findById(final String id){
		final Item item = itemRepository.findById(id);
		final User user = userRepository.findByUserId(item.getUserId());
		final Retrospective retrospective = retrospectiveRepository.findById(item.getRetrospectiveId());
		final ItemResponse itemResponse = new ItemResponse();
		BeanUtils.copyProperties(item, itemResponse);
		
		return null;
	}
	
	public List<Item> findByRetrospectiveId(final String projectId){
		return null;
	}
	
	public List<Item> findByUserId(final String projectId){
		return null;
	}
}
