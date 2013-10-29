package com.scrumretro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas 
 *
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, String> {

	public Project findByName(final String name);
}
