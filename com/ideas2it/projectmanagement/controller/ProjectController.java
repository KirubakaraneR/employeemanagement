package com.ideas2it.projectmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * We get user values from the project view and pass to projec
 * service to perform create, read, and update operations.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * We create project details by getting value project view
     * Details such as name, start date, end date
     * 
     * @param projectName - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean createProject(String name, LocalDate startDate,
            LocalDate endDate, String status) {
        return projectService.createProject(name, startDate, endDate,
                status);
    }

    /**
     * We get details of an individual project.
     *
     * @param id - Project id
     *
     * @return List of project value
     */
    public List<String> getIndividualProjectForDisplay(int id) {
        return projectService.getIndividualProjectForDisplay(id);
    }

    /**
     * We get all the project details for display.
     *
     * @return List of project values
     */
    public List<String> getProjectForDisplay() {
        return projectService.getProjectForDisplay();
    }

    /**
     * We update the name of the project.
     *
     * @param id - Project id
     * @param name - Project name
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateProjectName(int id, String name) {
        return projectService.updateProjectName(id, name);
    }

    /**
     * We update the start date of the project
     *
     * @param id - Project id
     * @param startDate - Project start date
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateStartDate(int id, LocalDate startDate) {
        return projectService.updateStartDate(id, startDate);
    }

    /**
     * We update the end date of the project
     *
     * @param id - Project id
     * @param endDate - Project end date
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateEndDate(int id, LocalDate endDate) {
        return projectService.updateEndDate(id, endDate);
    }

    /**
     * We update the project status like started or not
     *
     * @param id - Project id
     * @param status - Project status
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateProjectStatus(int id, String status) {
        return projectService.updateProjectStatus(id, status);
    }

    /**
     * We delete entire details of a project
     *
     * @param id - project id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteProject(int id) {
        return projectService.deleteProject(id);
    }

    /**
     * We assign list of employees to a project
     *
     * @param id - Project id
     * @param employeeIdList - List of employee id
     */
    public void assignEmployeeToProject(int id, List<String> employeeIdList) {
        projectService.assignEmployeeToProject(id, employeeIdList);
    }

    /**
     * We unassign list of employees from the project
     *
     * @param id - Project id
     * @param employeeIdList - List of employee id
     */
    public void unassignEmployeeFromProject(int id, List<String> employeeIdList) {
        projectService.unassignEmployeeFromProject(id, employeeIdList);
    }

    /**
     * We get the employee id list.
     * 
     * @param id - Project id
     * @param employeeIdList - List of employee Id
     *
     * @return List of employee id
     */
    public List<List<String>> getEmployeeIdList(int id, List<String> employeeIdList) {
        return projectService.getEmployeeIdList(id, employeeIdList);
    }

    /**
     * We display list of employees who are assigned to project.
     *
     * @param id - Project id
     *
     * @return List of Employee
     */
    public List<String> getAssignedEmployees(int id) {
        return projectService.getAssignedEmployees(id);
    }

    /**
     * We check whether the list is empty or not.
     *
     * @param projectIdList - List of employee id
     *
     * @return true if empty
     *         else false
     */
    public boolean checkIsEmpty(List<String> employeeIdList) {
        return projectService.checkIsEmpty(employeeIdList);
    }

    /**
     * We check whether the given project id exist or not.
     *
     * @param id - Project id
     *
     * @return true if project id exist
     *         else false
     */
    public boolean checkIdExistOrNot(int id) {
        return projectService.checkIdExistOrNot(id);
    }

    /**
     * We check whether the id is deleted.
     * 
     * @param id - Project id
     * 
     * @return true if the id is deleted
     *         else false
     */
    public boolean checkIdIsDeleted(int id) {
        return projectService.checkIdIsDeleted(id);
    }

    /**
     * We restore the deleted project.
     *
     * @param id - project id
     *
     * @return true if project is restored successfully
     *         else false
     */
    public boolean restoreProject(int id) {
        return projectService.restoreProject(id);
    }
}