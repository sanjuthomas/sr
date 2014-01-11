package com.scrumretro.web.service;

import static org.mockito.MockitoAnnotations.initMocks;

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
	}
	
	@Test
	public void shouldFindById(){
		
	}
}
