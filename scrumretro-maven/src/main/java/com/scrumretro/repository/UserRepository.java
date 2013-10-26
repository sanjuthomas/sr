package com.scrumretro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.scrumretro.repository.model.Project;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface UserRepository extends PagingAndSortingRepository<Project, String> {

}
