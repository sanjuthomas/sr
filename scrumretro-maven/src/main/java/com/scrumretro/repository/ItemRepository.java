package com.scrumretro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Item;

/**
 *  @author Sanju Thomas 
 * 
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
	
}
