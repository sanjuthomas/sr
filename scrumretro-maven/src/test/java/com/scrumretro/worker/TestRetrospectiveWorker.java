package com.scrumretro.worker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrumretro.exception.ScrumRetroRuntimeException;
import com.scrumretro.repository.ProjectRepository;
import com.scrumretro.repository.RetrospectiveRepository;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.security.authentication.ScrumRetroUser;
import com.scrumretro.security.util.SecurityContextUtil;
import com.scrumretro.web.model.RetrospectiveRequest;
import com.scrumretro.web.model.RetrospectiveResponse;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-root-context.xml", "classpath:test-servlet-context.xml" })
public class TestRetrospectiveWorker {
	
	private RetrospectiveWorker retrospectiveWorker;
	
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
		retrospectiveWorker = new RetrospectiveWorker();
		when(mockRetrospectiveRepository.findById(any(String.class))).thenReturn(createRetrospective());
		when(mockRetrospectiveRepository.save(any(Retrospective.class))).thenReturn(createRetrospective());
		final Project project = createProject();
		when(mockProjectRepository.findById(any(String.class))).thenReturn(project);
		retrospectiveWorker.setRetrospectiveRepository(mockRetrospectiveRepository);
		retrospectiveWorker.setProjectRepository(mockProjectRepository);
		
	}
	
	private void setupMockSecurityContextUtil(final RetrospectiveWorker retrospectiveWorker,final String userName){
		when(scrumRetroUser.getUsername()).thenReturn(userName);
		when(mockSecurityContextUtil.getUserProfile()).thenReturn(scrumRetroUser);
		retrospectiveWorker.setSecurityContextUtil(mockSecurityContextUtil);
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
	
	@Test
	public void shouldUpdateOpenForVote(){
		setupMockSecurityContextUtil(retrospectiveWorker,"info@scrumretro.com");
		RetrospectiveResponse retrospectiveResponse = retrospectiveWorker.openForVote("r1");
		assertEquals("r1", retrospectiveResponse.getId());
		assertEquals("Open For Voting", retrospectiveResponse.getStatusDisplayString());
		
	}
	
	@Test(expected=ScrumRetroRuntimeException.class)
	public void shouldFailUpdateOpenForVote(){
		setupMockSecurityContextUtil(retrospectiveWorker,"xyzinfo@scrumretro.com");
		retrospectiveWorker.openForVote("r1");
		
		
	}
	
	@Test
	public void shouldClose(){
		setupMockSecurityContextUtil(retrospectiveWorker,"info@scrumretro.com");
		RetrospectiveResponse retrospectiveResponse = retrospectiveWorker.close("r1");
		assertEquals("r1", retrospectiveResponse.getId());
		assertEquals("Closed", retrospectiveResponse.getStatusDisplayString());
		
	}
	
	@Test(expected=ScrumRetroRuntimeException.class)
	public void shouldFailFailClose(){
		setupMockSecurityContextUtil(retrospectiveWorker,"xyzinfo@scrumretro.com");
		retrospectiveWorker.close("r1");
		
		
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setId("pid");
		project.setName("pname");
		project.setDescription("pdescription");
		project.setOwner("info@scrumretro.com");
		return project;
	}

}
