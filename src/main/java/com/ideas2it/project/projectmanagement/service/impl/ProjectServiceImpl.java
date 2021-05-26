package com.ideas2it.project.projectmanagement.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.exceptions.ProjectException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.project.projectmanagement.dao.ProjectDao;
import com.ideas2it.project.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.project.projectmanagement.model.Project;
import com.ideas2it.project.projectmanagement.service.ProjectService;

/**
 * We perform create and update operation to pojo and also
 * pass the values to project dao to perform CRUD operation in DB.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao = new ProjectDaoImpl();
	private Loggers log = new Loggers(ProjectServiceImpl.class);

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public void createProject(String name, LocalDate startDate,
			LocalDate endDate, String status) throws ProjectException {
		boolean isChecked = projectDao.addOrUpdateProject(new Project(name, 
				Date.valueOf(startDate), Date.valueOf(endDate), status));  

		if (true == isChecked) {
			log.logInfo("New project values are created.");
		} else {
			log.logInfo("Issue in creating new project.");
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public Project getIndividualProjectForDisplay(int id) 
			throws ProjectException {
		Project project = null;

		try {
			project = projectDao.getProject(id);
		} catch (NullPointerException e) {

			if (null != project) {
				log.logInfo("Project values are fetched successfully for "
						+ "project id" + id);
			} else {
				log.logError("The project id " + id + " is not available "
						+ "to fetch.", e);
			}
		}
		return project;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public List<Project> getProjectForDisplay() throws ProjectException {
		List<Project> projectValues = null;

		try {
			projectValues = projectDao.getAllProject();
		} catch (NullPointerException e) {

			if (!projectValues.isEmpty()) {
				log.logInfo("Project values are fetched successfully.");
			} else {
				log.logError("There is an issue in fetching project values.", e);
			}
		}
		return projectValues;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public void updateProject(int id, String name, LocalDate startDate,
			LocalDate endDate, String status) throws ProjectException {
		boolean isChecked = false;

		try {
			Project project = projectDao.getProject(id);
			project.setId(id);
			project.setName(name);
			project.setStartDate(Date.valueOf(startDate));
			project.setEndDate(Date.valueOf(endDate));
			project.setStatus(status);
			isChecked = projectDao.addOrUpdateProject(project);  
		} catch (NullPointerException | InputMismatchException e) {
			if (true == isChecked) {
				log.logInfo("Values are updated successfully.");
			} else {
				log.logError("There is an issue in updating values.", e);
			}
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public boolean deleteProject(int id) throws ProjectException {
		Project project = projectDao.getProject(id); 
		project.setIsDeleted(true);
		List<Employee> employee = project.getEmployees();
		employee.clear();
		project.setEmployees(employee);
		boolean isChecked = projectDao.addOrUpdateProject(project);

		if (true == isChecked) {
			log.logInfo("The project is deleted successfully.");
		} else {
			log.logInfo("There is an issue in deleting project.");
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public void assignEmployeeToProject(int id, 
			List<String> employeeIdList) 
					throws ProjectException {

		EmployeeService employeeService = new EmployeeServiceImpl();
		List<Employee> employeeList = 
				employeeService.getEmployee(employeeIdList);
		Project project = projectDao.getProject(id);
		List<Employee> assignedEmployee = project.getEmployees();
		assignedEmployee.addAll(employeeList);
		try {
			project.setId(id);
			project.setEmployees(assignedEmployee);
			projectDao.addOrUpdateProject(project);
			log.logInfo("Employees are assigned successfully.");
		} catch (InputMismatchException e) {
			log.logError("The input values doesn't match while assigning", e);
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public void unassignEmployeeFromProject(int id, 
			List<String> employeeIdList) 
					throws ProjectException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		List<Employee> employeeList = 
				employeeService.getEmployee(employeeIdList);
		Project project = projectDao.getProject(id); 
		List<Employee> assignedEmployee = project.getEmployees();

		for (int index1 = 0; index1 < employeeList.size(); index1++) {

			for (int index2 = 0; index2 < assignedEmployee.size(); index2++) {

				if ((employeeList.get(index1).getId())
						.equals(assignedEmployee.get(index2).getId())) {
					assignedEmployee.remove(index2);
				}
			}
		}
		try {
			project.setId(id);
			project.setEmployees(assignedEmployee);
			projectDao.addOrUpdateProject(project); 
			log.logInfo("Employees are unassigned successfully.");
		} catch (InputMismatchException e) {
			log.logError("The input values doesn't match while "
					+ "unassigning", e);
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public List<List<String>> getEmployeeIdList(int id, 
			List<String> employeeIdList) 
					throws ProjectException {
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
	 * @throws ProjectException 
	 */
	@Override
	public List<String> getAssignedEmployees(int id) throws ProjectException {
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
	 * @throws ProjectException 
	 */
	@Override
	public List<Project> getProject(List<Integer> projectIdList) 
			throws ProjectException {
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
	 * @throws ProjectException 
	 */
	@Override
	public boolean checkIdExistOrNot(int id) throws ProjectException {
		int count = projectDao.getIdCount(id);
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public boolean checkIdIsDeleted(int id) throws ProjectException {
		int count = projectDao.getDeletedIdCount(id);
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectException 
	 */
	@Override
	public boolean restoreProject(int id) throws ProjectException {

		Project project = projectDao.getProject(id); 
		project.setIsDeleted(false);
		boolean isChecked = projectDao.addOrUpdateProject(project);
		if (true == isChecked) {
			log.logInfo("Project is restored successfully.");
		} else {
			log.logInfo("There is an issue in restoring project.");
		}
		return isChecked;
	}
}