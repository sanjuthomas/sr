package com.scrumretro.test;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import org.junit.Rule;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

public class BaseUnitTest {
	
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("scrumretro-test");

}
