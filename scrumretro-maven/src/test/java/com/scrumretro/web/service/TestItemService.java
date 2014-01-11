package com.scrumretro.web.service;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scrumretro.rest.Response;
import com.scrumretro.web.model.ItemResponse;
import com.scrumretro.worker.ItemWorker;


/**
 * 
 * @author Sanju Thomas
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class TestItemService {

	@Autowired
	private ApplicationContext applicationContext;

	private MockMvc mockMvc;
	
	@Autowired
	private ItemService itemService;
	
	@Mock
	private ItemWorker itemWorker;
	
	@Before
	public void setUp() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(itemService).build();
		this.itemService.setItemWorker(itemWorker);
		when(itemWorker.findById(any(String.class))).thenReturn(createItemResponse());
	}
	
	@Test
	public void shouldFindById() throws Exception{
		mockMvc.perform(get("/item/findById/{id}", "i1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(Response.APPLICATION_JSON_UTF8));
	}
	
	private ItemResponse createItemResponse(){
		final ItemResponse itemResponse = new ItemResponse();
		itemResponse.setId("i1");
		itemResponse.setDescription("description");
		itemResponse.setProjectName("projectName");
		itemResponse.setRetrospectiveName("retrospectiveName");
		return itemResponse;
	}
}
