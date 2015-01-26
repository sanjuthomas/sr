package com.scrumretro.web.service;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.scrumretro.rest.Response;
import com.scrumretro.test.IntegrationTest;
import com.scrumretro.web.model.request.UserRegistrationRequest;

/**
 * Integration test cases for UserService.
 *  
 * This testcase would wire UserService, UserWorker and UserRepository.
 * 
 * @author Ragil
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-root-context.xml","classpath:test-servlet-context.xml" })
@WebAppConfiguration
public class UserServiceIntegrationTest {

    private MockMvc mockMvc;
	
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("scrumretro-test");

	@Autowired
	private UserService userService;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldRegister() throws Exception{
		this.mockMvc.perform(
				post("/user/register/").content(createUserRequest().toString())
						.contentType(Response.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(
						content().contentType(Response.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$userId", is("ragilc@scrumretro.com")))
				.andExpect(jsonPath("$firstName", is("Ragil")))
				.andExpect(jsonPath("$lastName", is("Chandran")))
				.andExpect(jsonPath("$organization", is("organization")));
		
	}
	
	private UserRegistrationRequest createUserRequest() {
		final UserRegistrationRequest userRequest = new UserRegistrationRequest();
		userRequest.setUserId("ragilc@scrumretro.com");
		userRequest.setFirstName("Ragil");
		userRequest.setLastName("Chandran");
		userRequest.setPassword("password");
		userRequest.setConfirmPassword("password");
		userRequest.setOrganization("organization");
		return userRequest;
	}
	
}
