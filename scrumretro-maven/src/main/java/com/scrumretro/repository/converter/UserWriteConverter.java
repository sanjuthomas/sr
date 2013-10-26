package com.scrumretro.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.scrumretro.repository.model.User;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class UserWriteConverter implements Converter<User, DBObject> {

	public DBObject convert(final User user) {
		DBObject dbo = new BasicDBObject();
		dbo.put("_id", user.getEmailId());
		dbo.put("userDetail", user.getUserDetail());
		return dbo;
	}

}
