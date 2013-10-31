package com.scrumretro.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
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
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.enums.ItemType;
import com.scrumretro.repository.model.Item;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestItemRepositoryBehavior {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private ItemRepository itemRepository;

	
	@Test
	@ShouldMatchDataSet(location = "/testData/item/item-i1.json")
	public void shouldSaveOneProjectDocument() {
		itemRepository.save(createItem());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/item/item-i1.json"})
	public void shouldFindByRetrospectiveId(){
		List<Item> items = itemRepository.findByRetrospectiveId("234dqwer2wqer");
		assertNotNull(items);
		assertTrue(items.size() > 0);
		assertEquals("234dqwer2wqer", items.get(0).getRetrospectiveId());
	}
	
	private Item createItem(){
		final Item item = new Item();
		item.setItemType(ItemType.STOP_DOING);
		item.setDescription("This is test item created for retrospective r1");
		item.setRetrospectiveId("234dqwer2wqer");
		item.setUserId("info@scrumretro.com");
		item.setVotes(1);
		return  item;
	}
	

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { ItemRepository.class })
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
		
	}
}
