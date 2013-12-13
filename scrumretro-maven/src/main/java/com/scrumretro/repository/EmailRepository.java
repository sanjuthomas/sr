package com.scrumretro.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Email;
/**
 *  @author Ragil 
 * 
 */
public interface EmailRepository extends PagingAndSortingRepository<Email, String> {
	
	@Query("{ '_id' : ?0 }")
	Email findById(final String id);
}
