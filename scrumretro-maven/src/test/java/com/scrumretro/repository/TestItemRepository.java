package com.scrumretro.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import com.scrumretro.enums.ItemType;
import com.scrumretro.repository.model.Item;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestItemRepository extends BaseUnitTest{
	
	@Autowired
	private ApplicationContext applicationContext;

	
	@Autowired
	private ItemRepository itemRepository;

	
	@Test
	@ShouldMatchDataSet(location = "/testData/item/item-i1.json")
	public void shouldSave() {
		itemRepository.deleteAll();
		itemRepository.save(createItem());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/item/item-i1.json"})
	public void shouldFindByRetrospectiveId(){
		final List<Item> items = itemRepository.findByRetrospectiveId("234dqwer2wqer");
		assertNotNull(items);
		assertTrue(items.size() > 0);
		assertEquals("234dqwer2wqer", items.get(0).getRetrospectiveId());
	}
	
	@Test
	@UsingDataSet(locations = {"/testData/item/item-i2.json"})
	public void shouldFindById(){
		final Item item = itemRepository.findById("i2");
		assertNotNull(item);
		assertTrue(item.getVotedUsers().contains("info@scrumretro.com"));
		assertTrue(item.getVotedUsers().contains("user@scrumretro.com"));
		assertEquals("i2", item.getId());
	}
	
	
	
	private Item createItem(){
		final Item item = new Item();
		item.setItemType(ItemType.STOP_DOING);
		item.setDescription("This is test item created for retrospective r1");
		item.setRetrospectiveId("234dqwer2wqer");
		item.setUserId("info@scrumretro.com");
		final List<String> votedUsers = new ArrayList<String>();
		votedUsers.add("info@scrumretro.com");
		votedUsers.add("user@scrumretro.com");
		//item.setVotedUsers(votedUsers);
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
