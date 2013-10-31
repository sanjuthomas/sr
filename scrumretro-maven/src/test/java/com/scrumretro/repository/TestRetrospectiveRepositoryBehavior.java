package com.scrumretro.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.repository.model.User;
import com.scrumretro.repository.model.UserDetail;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestRetrospectiveRepositoryBehavior {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private RetrospectiveRepository retrospectiveRepository;
	
	@Before
	public void setUp(){
		this.retrospectiveRepository.deleteAll();
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/retrospective/retrospective-r1.json")
	public void shouldSaveRetrospective(){
		retrospectiveRepository.save(createRetrospective());
	}
	
//	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r1.json"})
	public void shouldFindByProjectId(){
		final List<Retrospective> retrospectives = retrospectiveRepository.findByProjectId("5270269044ae1440f787333a");
		assertNotNull(retrospectives);
		assertTrue(retrospectives.size() > 0);
		assertEquals("retrospective-r1", retrospectives.get(0).getName());
	}
	
//	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r1.json"})
	public void shouldFindByUserId(){
		final List<Retrospective> retrospectives = retrospectiveRepository.findByUserId("info@scrumretro.com");
		assertNotNull(retrospectives);
		assertTrue(retrospectives.size() > 0);
		assertEquals("retrospective-r1", retrospectives.get(0).getName());
	}
	
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setName("retrospective-r1");
		final User user = new User();
		user.setEmailId("info@scrumretro.com");
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("firstName");
		userDetail.setLastName("lastName");
		userDetail.setOrganization("organization");
		user.setUserDetail(userDetail);
		retrospective.setUser(user);
		final Project project = createProject();
		retrospective.setProject(project);
		return retrospective;
	}
	
	private Project createProject(){
		final Project project = new Project();
		project.setId("5270269044ae1440f787333a");
		project.setName("p1");
		project.setDescription("This is a test project called p1");
		project.setOrganization("o1");
		return project;
	}
	
	
	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { RetrospectiveRepository.class })
	@PropertySource("classpath:application.properties")
	static class MongoConfiguration extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "scrumretro-test";
		}

		@Override
		public Mongo mongo() {
			return new Fongo("mongo-test").getMongo();
		}

		@Override
		protected String getMappingBasePackage() {
			return "com.scrumretro.repository.mongo";
		}
		
		@Bean
		public CustomConversions customConversions() {
			List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
			converters.add(new Converter<Project, DBObject>() {
				@Override
				public DBObject convert(Project project) {
					DBObject dbo = new BasicDBObject();
					dbo.put("_id", project.getId());
					dbo.put("name", project.getName());
					dbo.put("description", project.getDescription());
					dbo.put("organization", project.getOrganization());
					return dbo;
				}
			});
			return new CustomConversions(converters);
		}
	}

}
