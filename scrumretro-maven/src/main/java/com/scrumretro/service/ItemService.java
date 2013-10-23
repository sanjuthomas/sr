package com.scrumretro.service;

import com.scrumretro.data.model.Item;

/**
 * Service class for Item CURD operation.
 * 
 */
public interface ItemService {

	/**
	 * @param item
	 * @return
	 */
	public String save(final Item item);
	
}
