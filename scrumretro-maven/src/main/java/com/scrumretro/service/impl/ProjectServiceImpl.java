package com.scrumretro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.scrumretro.data.model.Item;
import com.scrumretro.service.ProjectService;

@Repository
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	public String save(final Item item) {
		// TODO Auto-generated method stub
		return null;
	}
}
