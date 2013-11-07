package com.scrumretro.worker;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.scrumretro.enums.ItemType;
import com.scrumretro.repository.ItemRepository;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.Item;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;
import com.scrumretro.web.model.ItemResponse;

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
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Before
	public void setUp(){
		initMocks(this);
		itemWorker = new ItemWorker();
		final Item item = createItem();
		when(itemRepository.findById(any(String.class))).thenReturn(item);
		itemWorker.setItemRepository(itemRepository);
		final Retrospective retrospective = createRetrospective();
		when(retrospectiveRepository.findById(any(String.class))).thenReturn(retrospective);
		itemWorker.setRetrospectiveRepository(retrospectiveRepository);
		final User user = createUser();
		when(userRepository.findByUserId(any(String.class))).thenReturn(user);
		itemWorker.setUserRepository(userRepository);
		final Project project = createProject();
		when(projectRepository.findById(any(String.class))).thenReturn(project);
		itemWorker.setProjectRepository(projectRepository);
	}
	
	@Test
	public void shouldFindById(){
		final ItemResponse itemResponse = itemWorker.findById("i1");
		assertNotNull(itemResponse);
		assertEquals("i1", itemResponse.getId());
	}
	
	
	private Item createItem(){
		final Item item = new Item();
		item.setId("i1");
		item.setDescription("description");
		item.setItemType(ItemType.STOP_DOING);
		item.setRetrospectiveId("r1");
		item.setUserId("u1");
		return item;
	}
	
	private User createUser(){
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		user.setActive(true);
		user.setPassword("password");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("o1");
		user.setUserDetail(userDetail);
		return user;
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setId("p1");
		project.setDescription("description");
		project.setName("pname");
		final User user = createUser();
		project.setUser(user);
		return project;
	}
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setId("r1");
		retrospective.setName("rname");
		retrospective.setProjectId("p1");
		retrospective.setUserId("info@scrumretro.com");
		return retrospective;
	}

}
