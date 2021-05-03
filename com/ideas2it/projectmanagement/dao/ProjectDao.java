package com.ideas2it.projectmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.projectmanagement.model.Project;

/**
 * We create abstract method for all methods in project 
 * dao implementation
 */
public interface ProjectDao {
    
    /**
     * We perform addition or updation of project details to table.
     * Details such as name, start date, end date and status of project.
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
     * We assign list of employees to the project
     *
     * @param project - Project pojo
     */
    public void assignEmployeeToProject(Project project);
}