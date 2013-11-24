package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas 
 *
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, String> {

	Project findByName(final String name);
	
	List<Project> findByOwner(final String userId);
	
	@Query("{ '_id' : ?0 }")
	Project findById(final String id);
	
	<S extends Project> S save(S project);
}
