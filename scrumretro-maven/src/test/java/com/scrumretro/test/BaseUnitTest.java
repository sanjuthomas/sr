package com.scrumretro.test;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Rule;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

public class BaseUnitTest {
	
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("scrumretro-test");
	
	/**
	 * Convert the given json file to an object of given type.
	 * 
	 * @param jsonFile
	 * @param clazz
	 * @return
	 */
	protected Object toObject(final File jsonFile, final Class<?> clazz) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonFile, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Convert the given object to json
	 * 
	 * @param object
	 * @return
	 */
	protected String toJSON(final Object object){
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
