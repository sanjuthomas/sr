package com.scrumretro.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Retrospective;

/**
 *  @author Sanju Thomas 
 * 
 */
public interface RetrospectiveRepository extends PagingAndSortingRepository<Retrospective, String> {
	
	public List<Retrospective> findByProjectId(final String projectId);
	
	public List<Retrospective> findByUserId(final String userId);
	
}
