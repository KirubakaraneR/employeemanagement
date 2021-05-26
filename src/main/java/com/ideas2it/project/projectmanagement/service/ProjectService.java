package com.ideas2it.project.projectmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.exceptions.ProjectException;
import com.ideas2it.project.projectmanagement.model.Project;

/**
 * We create abstract method for all methods in project 
 * service implementation.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public interface ProjectService {

    /**
     * We create project details.
     * Details such as name, start date, end date
     * 
     * @param projectName - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     *
     * @return true if datas are successfully added to table
     *         else false
     * @throws ProjectException 
     */
    public void createProject(String name, LocalDate startDate,
            LocalDate endDate, String status) throws ProjectException;

    /**
     * We get details of an individual project.
     *
     * @param id - Project id
     *
     * @return Project Pojo
     * @throws ProjectException 
     */
    public Project getIndividualProjectForDisplay(int id) throws ProjectException;

    /**
     * We get all the project details for display.
     *
     * @return List of project value
     * @throws ProjectException 
     */
    public List<Project> getProjectForDisplay() throws ProjectException;

    /**
     * We update the project status like started or not
     *
     * @param id - Project id
     * @param status - Project status
     * @throws ProjectException 
     */
    public void updateProject(int id, String name, LocalDate startDate,
    		LocalDate endDate, String status) throws ProjectException;

    /**
     * We delete entire details of a project
     *
     * @param id - project id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     * @throws ProjectException 
     */
    public boolean deleteProject(int id) throws ProjectException;

    /**
     * We assign list of employees to a project
     *
     * @param id - Project id
     * @param employeeIdList - List of employee id
     * @throws ProjectException 
     */
    public void assignEmployeeToProject(int id, List<String> employeeIdList) throws ProjectException;

    /**
     * We unassign list of employees from the project
     *
     * @param id - Project id
     * @param employeeIdList - List of employee id
     * @throws ProjectException 
     */
    public void unassignEmployeeFromProject(int id, List<String> employeeIdList) throws ProjectException;
  
    /**
     * We get the employee id list.
     * 
     * @param id - Project id
     * @param employeeIdList - List of employee Id
     *
     * @return List of employee id
     * @throws ProjectException 
     */
    public List<List<String>> getEmployeeIdList(int id, List<String> employeeIdList) throws ProjectException;

    /**
     * We display list of employees who are assigned to project.
     *
     * @param id - Project id
     *
     * @return List of Employees
     * @throws ProjectException 
     */
    public List<String> getAssignedEmployees(int id) throws ProjectException;
   
    /**
     * We get the project details which matchs with the id list we are
     * passing. This method is called from employeeServiceImpl.
     *
     * @param projectIdList - List of project id
     *
     * @return List of project details
     * @throws ProjectException 
     */
    public List<Project> getProject(List<Integer> projectIdList) throws ProjectException;

    /**
     * We check whether the list is empty or not.
     *
     * @param projectIdList - List of employee id
     *
     * @return true if empty
     *         else false
     */
    public boolean checkIsEmpty(List<String> employeeIdList);

    /**
     * We check whether the given project id exist or not.
     *
     * @param id - Project id
     *
     * @return true if project id exist
     *         else false
     * @throws ProjectException 
     */
    public boolean checkIdExistOrNot(int id) throws ProjectException;

    /**
     * We check whether the id is deleted.
     * 
     * @param id - Project id
     * 
     * @return true if the id is deleted
     *         else false
     * @throws ProjectException 
     */
    public boolean checkIdIsDeleted(int id) throws ProjectException;

    /**
     * We restore the deleted project.
     *
     * @param id - project id
     *
     * @return true if project is restored successfully
     *         else false
     * @throws ProjectException 
     */
    public boolean restoreProject(int id) throws ProjectException;
}