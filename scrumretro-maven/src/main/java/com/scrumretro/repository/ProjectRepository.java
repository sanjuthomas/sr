package com.scrumretro.repository;

import com.scrumretro.repository.mongo.model.Project;
/**
 * 
 *
 */
public interface ProjectRepository {

	/**
	 * 
	 * @param item
	 * @return
	 */
	public void insert(final Project project);
}
