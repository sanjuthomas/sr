package com.scrumretro.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;
import com.scrumretro.repository.model.User;

public class UserWriteConverter implements Converter<User, DBObject> {

	public DBObject convert(final User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
