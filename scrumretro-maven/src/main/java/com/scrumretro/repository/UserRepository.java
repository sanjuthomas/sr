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

	User findByUserId(final String userId);
	
	@Query("{ active : true, userDetail.organization : ?0 }")
	List<User> findUsersByOrgranization(final String organization);
}
