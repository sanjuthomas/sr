package com.scrumretro.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.scrumretro.repository.model.Item;

/**
 * To save ItemType display string in the repository.
 * 
 * @author Sanju Thomas
 *
 */
public class ItemWriteConverter implements Converter<Item, DBObject> {

	public DBObject convert(final Item item) {
		DBObject dbo = new BasicDBObject();
		dbo.put("description", item.getDescription());
		dbo.put("itemType", item.getItemType().getDisplayString());
		dbo.put("userId", item.getUserId());
		dbo.put("retrospectiveId", item.getRetrospectiveId());
		dbo.put("votes", item.getVotes());
		return dbo;
	}
	
}
