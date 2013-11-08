package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.User;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	@Query("{ userId: ?0, password : ?1 }")
	User findByUserIdAndPassword(final String emailId,final String password);
		
	User findByUserId(final String emailId);
	
	@Query("{ active : true }")
	List<User> findActiveList();
}
