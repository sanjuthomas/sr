package com.scrumretro.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * @author Ragil 
 */
public class TestUIDGenerator {

	@Test
	public void shouldReturnUniqueIds() {
		final UIDGenerator uidGenerator = new UIDGenerator();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 25; i++) {
			final String uid = uidGenerator.getValue();
			assertEquals(false, list.contains(uid));
			list.add(uid);
		}
	}
	
	@Test
	public void shouldAppendPrefix() {
		   final UIDGenerator uidGenerator = new UIDGenerator();
		   final String prefix = "ABC";
			assertEquals(true, uidGenerator.getValue(prefix).startsWith(prefix));
			
	}
}
