package com.scrumretro.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.mongodb.Mongo;
import com.scrumretro.enums.RetrospectiveStatus;
import com.scrumretro.repository.model.Retrospective;
import com.scrumretro.test.BaseUnitTest;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestRetrospectiveRepository extends BaseUnitTest{
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private RetrospectiveRepository retrospectiveRepository;
	
	
	@Test
	@ShouldMatchDataSet(location = "/testData/retrospective/retrospective-r1.json")
	public void shouldSaveRetrospective(){
		retrospectiveRepository.deleteAll();
		retrospectiveRepository.save(createRetrospective());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r2.json"})
	public void shouldFindById(){
		final Retrospective retrospective = retrospectiveRepository.findById("r2");
		assertNotNull(retrospective);
		assertEquals("r2", retrospective.getId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r2.json"})
	public void shouldNotFindById(){
		final Retrospective retrospective = retrospectiveRepository.findById("r3s2");
		assertNull(retrospective);
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r1.json"})
	public void shouldFindByProjectId(){
		final List<Retrospective> retrospectives = retrospectiveRepository.findByProjectId("5270269044ae1440f787333a");
		assertNotNull(retrospectives);
		assertTrue(retrospectives.size() > 0);
		assertEquals("retrospective-r1", retrospectives.get(0).getName());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/retrospective/retrospective-r1.json"})
	public void shouldNotFindByProjectId(){
		final List<Retrospective> retrospectives = retrospectiveRepository.findByProjectId("044ae1440f787333a");
		assertEquals(0, retrospectives.size());
	}
	
	private Retrospective createRetrospective(){
		final Retrospective retrospective = new Retrospective();
		retrospective.setName("retrospective-r1");
		retrospective.setProjectId("5270269044ae1440f787333a");
		retrospective.setRetrospectiveStatus(RetrospectiveStatus.OPEN);
		return retrospective;
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
			return "com.scrumretro.*";
		}
		
	}

}
