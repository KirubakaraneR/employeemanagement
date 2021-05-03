package com.ideas2it.projectmanagement.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * We perform create and update operation to pojo and also
 * pass the values to project dao to perform CRUD operation in DB
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();
    
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createProject(String name, LocalDate startDate,
            LocalDate endDate, String status) {
        return projectDao.addOrUpdateProject(new Project(name, Date.valueOf(startDate), 
                Date.valueOf(endDate), status));
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualProjectForDisplay(int id) {
        Project project = projectDao.getProject(id);
        List<String> projectDetails = new ArrayList<String>();
        projectDetails.add(project.toString());
        return projectDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getProjectForDisplay() {
        List<Project> projectValues = projectDao.getAllProject();
        List<String> projectList = new ArrayList<String>();
        
        for (Project values : projectValues) {
            projectList.add(values.toString());
        }
        return projectList;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProjectName(int id, String name) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setName(name);
        return projectDao.addOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateStartDate(int id, LocalDate startDate) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setStartDate(Date.valueOf(startDate));
        return projectDao.addOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateEndDate(int id, LocalDate endDate) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setEndDate(Date.valueOf(endDate));
        return projectDao.addOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProjectStatus(int id, String status) {
        Project project = projectDao.getProject(id); 
        project.setId(id);
        project.setStatus(status);
        return projectDao.addOrUpdateProject(project);  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteProject(int id) {
        Project project = projectDao.getProject(id); 
        project.setId(id);
        project.setIsDeleted(true);
        return projectDao.addOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignEmployeeToProject(int id, List<String> employeeIdList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employeeList = employeeService.getEmployee(employeeIdList);
        Project project = projectDao.getProject(id);
        List<Employee> assignedEmployee = project.getEmployees();
        assignedEmployee.addAll(employeeList);
        project.setId(id);
        project.setEmployees(assignedEmployee);
        projectDao.assignEmployeeToProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignEmployeeFromProject(int id, List<String> employeeIdList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employeeList = employeeService.getEmployee(employeeIdList);
        Project project = projectDao.getProject(id); 
        List<Employee> assignedEmployee = project.getEmployees();
        
        for (int index1 = 0; index1 < employeeList.size(); index1++) {
 
            for (int index2 = 0; index2 < assignedEmployee.size(); index2++) {
 
                if ((employeeList.get(index1).getId()).equals(assignedEmployee.get(index2).getId())) {
                    assignedEmployee.remove(index2);
                }
            }
        }
        project.setId(id);
        project.setEmployees(assignedEmployee);
        projectDao.assignEmployeeToProject(project); 
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<String>> getEmployeeIdList(int id, List<String> employeeIdList) {
        Project project = projectDao.getProject(id);
        List<Employee> employeeValues = project.getEmployees();
        List<String> employeeId = new ArrayList<String>();
        List<String> employeeIdForAssign = new ArrayList<String>();
        List<String> employeeIdForUnassign = new ArrayList<String>();
        List<List<String>> employee = new ArrayList<List<String>>();
        boolean isChecked;
     
        if (null == employeeValues) {
            Collections.copy(employeeIdForAssign, employeeIdList);
        } else {

            for (Employee employeeList : employeeValues) {
                employeeId.add(employeeList.getId());
            }
        
            for (String unassignedId : employeeIdList) {
                isChecked = false;

                for (String assignedId : employeeId) {
                
                    if (unassignedId.equals(assignedId)) {
                        isChecked = true;
                        employeeIdForUnassign.add(unassignedId);
                    }
                }
        
                if (false == isChecked) {
                    employeeIdForAssign.add(unassignedId);
                }
            }
        }
        employee.add(employeeIdForAssign);
        employee.add(employeeIdForUnassign);
        return employee;
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAssignedEmployees(int id) {
        Project project = projectDao.getProject(id);
        List<Employee> employeeList = project.getEmployees();
        List<String> employeeDetails = new ArrayList<String>();

        for (Employee employee : employeeList) {
            employeeDetails.add(employee.toString());
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProject(List<Integer> projectIdList) {
        List<Project> projectList = projectDao.getAllProject();
        List<Project> projectDetails = new ArrayList<Project>();
 
        for (Project project : projectList) {

            if (projectIdList.contains(project.getId())) {
                projectDetails.add(project);
            }
        }
        return projectDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkIsEmpty(List<String> employeeIdList) {
        return employeeIdList.isEmpty();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkIdExistOrNot(int id) {
        Project project = projectDao.getProject(id);
        return (null == project) ? false : true;
    }
}