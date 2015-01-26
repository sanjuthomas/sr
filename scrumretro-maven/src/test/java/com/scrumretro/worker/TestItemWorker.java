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
import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.security.util.SecurityContextUtil;
import com.scrumretro.web.model.request.ItemRequest;
import com.scrumretro.web.model.response.ItemResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestItemWorker {
	
	private ItemWorker itemWorker;
	
	@Mock
	private ItemRepository mockItemRepository;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private RetrospectiveRepository mockRetrospectiveRepository;
	
	@Mock
	private ProjectRepository mockProjectRepository;
	
	@Mock
	private SecurityContextUtil mockSecurityContextUtil;
	
	@Mock
	private ScrumRetroUser scrumRetroUser;
	
	@Before
	public void setUp(){
		initMocks(this);
		itemWorker = new ItemWorker();
		setUpMockItemRepository(itemWorker);
		setUpMockRetrospectiveRepository(itemWorker);
		setUpMockUserRepository(itemWorker);
		setUpMockProjectRepository(itemWorker);
		setupMockSecurityContextUtil(itemWorker);
	}
	
	private void setupMockSecurityContextUtil(final ItemWorker itemWorker){
		when(scrumRetroUser.getUsername()).thenReturn("info@scrumretro.com");
		when(mockSecurityContextUtil.getUserProfile()).thenReturn(scrumRetroUser);
		itemWorker.setSecurityContextUtil(mockSecurityContextUtil);
	}
	
	private void setUpMockProjectRepository(final ItemWorker itemWorker){
		final Project project = createProject();
		when(mockProjectRepository.findById(any(String.class))).thenReturn(project);
		itemWorker.setProjectRepository(mockProjectRepository);
	}
	
	private void setUpMockRetrospectiveRepository(final ItemWorker itemWorker){
		final Retrospective retrospective = createRetrospective();
		when(mockRetrospectiveRepository.findById(any(String.class))).thenReturn(retrospective);
		itemWorker.setRetrospectiveRepository(mockRetrospectiveRepository);
	}
	
	private void setUpMockUserRepository(final ItemWorker itemWorker){
		final User user = createUser();
		when(mockUserRepository.findByUserId(any(String.class))).thenReturn(user);
		itemWorker.setUserRepository(mockUserRepository);
	}
	
	private void setUpMockItemRepository(final ItemWorker itemWorker){
		final Item item = createItem();
		final List<Item> items = new ArrayList<Item>();
		items.add(item);
		when(mockItemRepository.findById(any(String.class))).thenReturn(item);
		when(mockItemRepository.save(any(Item.class))).thenReturn(item);
		when(mockItemRepository.findByRetrospectiveId(any(String.class))).thenReturn(items);
		when(mockItemRepository.findByUserId(any(String.class))).thenReturn(items);
		itemWorker.setItemRepository(mockItemRepository);
	}
	
	@Test
	public void shouldSaveItem(){
		final ItemResponse itemResponse = itemWorker.save(createItemRequest());
		assertEquals("pname", itemResponse.getProjectName());
		assertEquals("rname", itemResponse.getRetrospectiveName());
		assertEquals("Stop Doing", itemResponse.getItemTypeDisplayString());
	}
	
	private ItemRequest createItemRequest(){
		final ItemRequest itemRequest = new ItemRequest();
		itemRequest.setItemType(ItemType.START_DOING);
		itemRequest.setDescription("description");
		itemRequest.setRetrospectiveId("r1");
		return itemRequest;
	}
	
	@Test
	public void shouldFindById(){
		final ItemResponse itemResponse = itemWorker.findById("i1");
		assertNotNull(itemResponse);
		assertEquals("i1", itemResponse.getId());
	}
	
	@Test
	public void shouldFindByUserId(){
		final List<ItemResponse> itemResponses = itemWorker.findByUserId("info@scrumretro.com");
		assertNotNull(itemResponses);
		assertEquals(1, itemResponses.size());
		for(final ItemResponse itemResponse : itemResponses){
			validateItemResponse(itemResponse);
		}
	}
	
	@Test
	public void shouldFindByRetrospectiveId(){
		final List<ItemResponse> itemResponses = itemWorker.findByRetrospectiveId("r1");
		assertNotNull(itemResponses);
		assertEquals(1, itemResponses.size());
		for(final ItemResponse itemResponse : itemResponses){
			validateItemResponse(itemResponse);
		}
	}
	
	private void validateItemResponse(final ItemResponse itemResponse){
		assertEquals("i1", itemResponse.getId());
		assertEquals("description", itemResponse.getDescription());
		assertEquals("Stop Doing", itemResponse.getItemTypeDisplayString());
		assertEquals("rname", itemResponse.getRetrospectiveName());
		assertEquals("pname", itemResponse.getProjectName());
		assertEquals("lastName, firstName", itemResponse.getOwnerDisplayName());
		assertEquals(2, itemResponse.getVoteCount().intValue());
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
		project.setOwner("testuser@scrumretro.com");
		return project;
	}
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setId("r1");
		retrospective.setName("rname");
		retrospective.setProjectId("p1");
		return retrospective;
	}

}
