package com.scrumretro.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.enums.ItemType;
import com.scrumretro.repository.model.Item;

public class ItemRepositoryBehavior {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"scrumretro-test");

	@Autowired
	private ItemRepository itemRepository;

	@Before
	public void setUp(){
		itemRepository.deleteAll();
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/item/item-i1.json")
	public void shouldSaveOneProjectDocument() {
		itemRepository.save(createItem());
	}
	
	
	private Item createItem(){
		final Item item = new Item();
		item.setItemType(ItemType.STOP_DOING);
		item.setDescription("This is test item created for retrospective r1");
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