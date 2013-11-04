package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Item;

/**
 *  @author Sanju Thomas 
 * 
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
	
	public List<Item> findByRetrospectiveId(final String retrospectiveId);
	
	@Query("{ '_id' : ?0 }")
	public Item findById(final String id);
	
}
