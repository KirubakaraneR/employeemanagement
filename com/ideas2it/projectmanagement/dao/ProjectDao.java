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
     * We create project details by updating values to table.
     * Details such as name, start date, end date
     * 
     * @param project - Project pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean createProject(Project project);

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
     * We update the name of the project in the table.
     *
     * @param project - Project pojo
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateProjectDetails(Project project);

    /**
     * We update the start date of the project in the table only
     * if the project status is "no".
     *
     * @param project - Project pojo
     *
     * @return true if data is successfully updated to table
     *         else false
     */
    public boolean updateProjectStartDate(Project project);

    /**
     * We delete the details of the project in the table.
     *
     * @param id - Project id
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean deleteProject(int id);

    /**
     * We get project values from the table
     *
     * @param projectId - Project Id
     *
     * @return Project 
     */
    public Project getProjectEmployee(int id);

    /**
     * We assign list of employees to the project
     *
     * @param project - Project pojo
     */
    public void assignEmployeeToProject(Project project);

    /**
     * We unassign list of employees to the project
     *
     * @param project - Project pojo
     */
    public void unassignEmployeeFromProject(Project project);

    /**
     * We check whether the given project id exist or not.
     *
     * @param id - Project id
     *
     * @return count of the project id
     */
    public int checkIdExistOrNot(int id);
}