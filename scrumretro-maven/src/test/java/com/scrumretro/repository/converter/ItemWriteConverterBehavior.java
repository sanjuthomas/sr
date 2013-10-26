package com.scrumretro.repository.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBObject;
import com.scrumretro.enums.ItemType;
import com.scrumretro.repository.model.Item;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class ItemWriteConverterBehavior {
	
	private ItemWriteConverter itemWriteConverter;
	
	@Before
	public void setUp(){
		itemWriteConverter = new ItemWriteConverter();
	}
	
	@Test
	public void shouldConvertItemCorrectly(){
		final DBObject dbObject = itemWriteConverter.convert(createItem());
		assertEquals(ItemType.STOP_DOING.getDisplayString(), dbObject.get("itemType"));
		assertEquals("This is test item created for retrospective r1", dbObject.get("description"));
	}
	
	private Item createItem(){
		final Item item = new Item();
		item.setItemType(ItemType.STOP_DOING);
		item.setDescription("This is test item created for retrospective r1");
		return item;
	}

}
