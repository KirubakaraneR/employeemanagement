package com.ideas2it.project.projectmanagement.dao;

import java.util.List;

import com.ideas2it.project.exceptions.ProjectException;
import com.ideas2it.project.projectmanagement.model.Project;

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
     * @throws ProjectException 
     */
    public boolean addOrUpdateProject(Project project) throws ProjectException;

    /**
     * We get all the project details for display from the table.
     *
     * @return List of project values 
     * @throws ProjectException 
     */
    public List<Project> getAllProject() throws ProjectException;

    /**
     * We get individual the project details for display from the table.
     *
     * @return project pojo
     * @throws ProjectException 
     */
    public Project getProject(int id) throws ProjectException;

    /**
     * We get the id count for given project id.
     *
     * @param id - Project id
     *
     * @return count
     * @throws ProjectException 
     */
    public int getIdCount(int id) throws ProjectException;

    /**
     * We get the id count of deleted id for given project id.
     *
     * @param id - Project id
     *
     * @return count
     * @throws ProjectException 
     */
    public int getDeletedIdCount(int id) throws ProjectException;
}