package com.scrumretro.repository.mongo;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.scrumretro.repository.mongo.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */

@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = { Project.class })
@PropertySource("classpath:application.properties")
public abstract class MongoBaseUnitTest extends AbstractMongoConfiguration {

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("scrumretro-test");
	
	@Autowired private ApplicationContext applicationContext;

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
