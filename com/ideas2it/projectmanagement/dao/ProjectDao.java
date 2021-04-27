package com.ideas2it.projectmanagement.dao;

import com.ideas2it.projectmanagement.model.Project;

/**
 * We create abstract method for all methods in project 
 * dao implementation
 */
public interface ProjectDao {
    
    /**
     * We create project details by updating values to table.
     * Details such as name, start date, end date
     * 
     * @param project - Project pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean createProject(Project project);
}