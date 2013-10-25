package com.scrumretro.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;
import com.scrumretro.repository.model.Item;

public class ItemWriteConverter implements Converter<Item, DBObject>{

	public DBObject convert(final Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
