package com.scrumretro.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestItemType {

	@Test
	public void shouldReturnItemTypeDisplayString(){
		assertEquals("Stop Doing", ItemType.STOP_DOING.getDisplayString());
	}
}
