package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Project;
import com.scrumretro.repository.model.User;

/**
 * 
 * @author Sanju Thomas 
 *
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, String> {

	public Project findByName(final String name);
	
	public List<Project> findByUser(final User user);
	
	@Query("{ 'user._id' : ?0 }")
	public List<Project> findByUserId(final String userId);
}
