package com.scrumretro.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.scrumretro.repository.ItemRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Item;

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

	public Item findById(final String id){
		return null;
	}
	
	public List<Item> findByRetrospectiveId(final String projectId){
		return null;
	}
	
	public List<Item> findByUserId(final String projectId){
		return null;
	}
}
