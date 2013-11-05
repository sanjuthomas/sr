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

	@Query("{ emailId: ?0, password : ?1 }")
	public User findByUserIdAndPassword(final String emailId,final String password);
	
	public User findByEmailId(final String emailId);
		
	@Query("{ emailId : ?0 }")
	public User findByUserId(final String emailId);
	
	@Query("{ active : true }")
	public List<User> findActiveList();
}
