package com.scrumretro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Retrospective;

/**
 *  @author Sanju Thomas 
 * 
 */
public interface RetrospectiveRepository extends PagingAndSortingRepository<Retrospective, String> {
	
}
