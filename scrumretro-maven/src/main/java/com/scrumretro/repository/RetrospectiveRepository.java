package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Retrospective;

/**
 *  @author Sanju Thomas 
 * 
 */
public interface RetrospectiveRepository extends PagingAndSortingRepository<Retrospective, String> {
	
	@Query("{ 'project._id' : ?0 }")
	public List<Retrospective> findByProjectId(final String projectId);
	
	@Query("{ 'user._id' : ?0 }")
	public List<Retrospective> findByUserId(final String userId);
	
}
