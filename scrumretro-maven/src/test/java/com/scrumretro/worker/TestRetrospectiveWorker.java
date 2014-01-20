package com.scrumretro.worker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.web.model.RetrospectiveResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestRetrospectiveWorker {
	
	private RetrospectiveWorker retrospectiveWorker;
	
	@Mock
	private RetrospectiveRepository mockRetrospectiveRepository;
	
	@Before
	public void setUp(){
		initMocks(this);
		retrospectiveWorker = new RetrospectiveWorker();
		when(mockRetrospectiveRepository.findById(any(String.class))).thenReturn(createRetrospective());
		retrospectiveWorker.setRetrospectiveRepository(mockRetrospectiveRepository);
		
	}
	
	@Test
	public void shouldFindById(){
		RetrospectiveResponse retrospectiveResponse = retrospectiveWorker.findById("r1");
		assertEquals("r1", retrospectiveResponse.getId());
		assertEquals("rname", retrospectiveResponse.getName());
		assertEquals("p1", retrospectiveResponse.getProjectId());
	}
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setId("r1");
		retrospective.setName("rname");
		retrospective.setProjectId("p1");
		return retrospective;
	}

}
