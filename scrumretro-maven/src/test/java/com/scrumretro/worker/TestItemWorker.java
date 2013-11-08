package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

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
		setUpMockItemRepository(itemWorker);
		setUpMockRetrospectiveRepository(itemWorker);
		setUpMockUserRepository(itemWorker);
		setUpMockProjectRepository(itemWorker);
	}
	
	private void setUpMockProjectRepository(final ItemWorker itemWorker){
		final Project project = createProject();
		when(projectRepository.findById(any(String.class))).thenReturn(project);
		itemWorker.setProjectRepository(projectRepository);
	}
	
	private void setUpMockRetrospectiveRepository(final ItemWorker itemWorker){
		final Retrospective retrospective = createRetrospective();
		when(retrospectiveRepository.findById(any(String.class))).thenReturn(retrospective);
		itemWorker.setRetrospectiveRepository(retrospectiveRepository);
	}
	
	private void setUpMockUserRepository(final ItemWorker itemWorker){
		final User user = createUser();
		when(userRepository.findByUserId(any(String.class))).thenReturn(user);
		itemWorker.setUserRepository(userRepository);
	}
	
	private void setUpMockItemRepository(final ItemWorker itemWorker){
		final Item item = createItem();
		final List<Item> items = new ArrayList<Item>();
		items.add(item);
		when(itemRepository.findById(any(String.class))).thenReturn(item);
		when(itemRepository.findByRetrospectiveId(any(String.class))).thenReturn(items);
		itemWorker.setItemRepository(itemRepository);
	}
	
	@Test
	public void shouldFindById(){
		final ItemResponse itemResponse = itemWorker.findById("i1");
		assertNotNull(itemResponse);
		assertEquals("i1", itemResponse.getId());
	}
	
	@Test
	public void shouldFindByRetrospectiveId(){
		final List<ItemResponse> itemResponses = itemWorker.findByRetrospectiveId("r1");
		assertNotNull(itemResponses);
		assertEquals(1, itemResponses.size());
		assertEquals("i1", itemResponses.get(0).getId());
		assertEquals("description", itemResponses.get(0).getDescription());
		assertEquals("Stop Doing", itemResponses.get(0).getItemTypeDisplayString());
		assertEquals("rname", itemResponses.get(0).getRetrospectiveName());
		assertEquals("pname", itemResponses.get(0).getProjectName());
		assertEquals("lastName, firstName", itemResponses.get(0).getOwnerDisplayName());
		assertEquals(2, itemResponses.get(0).getVoteCount().intValue());
	}
	
	
	private Item createItem(){
		final Item item = new Item();
		item.setId("i1");
		item.setDescription("description");
		item.setItemType(ItemType.STOP_DOING);
		item.setRetrospectiveId("r1");
		item.setUserId("u1");
		final List<String> votedUsers = new ArrayList<String>();
		votedUsers.add("info@scrumretro.com");
		votedUsers.add("user@scrumretro.com");
		item.setVotedUsers(votedUsers);
		return item;
	}
	
	private User createUser(){
		final User user = new User();
		user.setUserId("info@scrumretro.com");
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
