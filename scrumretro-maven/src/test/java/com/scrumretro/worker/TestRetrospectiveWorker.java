package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.web.model.RetrospectiveRequest;
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
		when(mockRetrospectiveRepository.save(any(Retrospective.class))).thenReturn(createRetrospective());
		retrospectiveWorker.setRetrospectiveRepository(mockRetrospectiveRepository);
		
	}
	
	@Test
	public void shouldFindById(){
		RetrospectiveResponse retrospectiveResponse = retrospectiveWorker.findById("r1");
		assertEquals("r1", retrospectiveResponse.getId());
		assertEquals("rname", retrospectiveResponse.getName());
		assertEquals("p1", retrospectiveResponse.getProjectId());
	}
	
	@Test
	public void shouldSave(){
		RetrospectiveResponse retrospectiveResponse = retrospectiveWorker.save(createRetrospectiveRequest());
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
	
	private RetrospectiveRequest createRetrospectiveRequest(){
		final RetrospectiveRequest retrospectiveRequest = new RetrospectiveRequest();
		retrospectiveRequest.setId("r1");
		retrospectiveRequest.setName("rname");
		retrospectiveRequest.setProjectId("p1");
		return retrospectiveRequest;
	}

}
