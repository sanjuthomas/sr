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
	
	List<Item> findByRetrospectiveId(final String retrospectiveId);
	
	@Query("{ '_id' : ?0 }")
	Item findById(final String id);
	
	List<Item> findByUserId(final String userId);
	
}
