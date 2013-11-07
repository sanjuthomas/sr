package com.scrumretro.worker;

import org.junit.Before;
import org.mockito.Mock;

import com.scrumretro.repository.ItemRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Item;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestItemWorker {
	
	private ItemWorker itemWorker;
	
	@Mock
	private ItemRepository itemRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RetrospectiveRepository retrospectiveRepository;
	
	@Before
	public void setUp(){
		itemWorker = new ItemWorker();
		itemWorker.setItemRepository(itemRepository);
		itemWorker.setRetrospectiveRepository(retrospectiveRepository);
		itemWorker.setUserRepository(userRepository);
	}
	
	
	

}
