package com.scrumretro.repository;

import com.scrumretro.repository.mongo.model.Item;

/**
 * Service class for Item CURD operation.
 * 
 */
public interface ItemRepository {

	/**
	 * @param item
	 * @return
	 */
	public String save(final Item item);
	
}
