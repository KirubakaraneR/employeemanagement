package com.ideas2it.projectmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.projectmanagement.model.Project;

/**
 * We create abstract method for all methods in project 
 * dao implementation.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public interface ProjectDao {
    
    /**
     * We perform addition or updation of project details to table.
     * 
     * @param project - Project pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean addOrUpdateProject(Project project);

    /**
     * We get all the project details for display from the table.
     *
     * @return List of project values
     */
    public List<Project> getAllProject();

    /**
     * We get individual the project details for display from the table.
     *
     * @return project pojo
     */
    public Project getProject(int id);

    /**
     * We get the id count for given project id.
     *
     * @param id - Project id
     *
     * @return count
     */
    public int getIdCount(int id);

    /**
     * We get the id count of deleted id for given project id.
     *
     * @param id - Project id
     *
     * @return count
     */
    public int getDeletedIdCount(int id);
}