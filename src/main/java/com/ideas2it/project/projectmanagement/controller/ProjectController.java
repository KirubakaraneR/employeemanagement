package com.ideas2it.project.projectmanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ideas2it.project.employeemanagement.controller.EmployeeController;
import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.exceptions.UserDefinedException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.project.projectmanagement.model.Project;
import com.ideas2it.project.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.project.projectmanagement.service.ProjectService;

/**
 * Servlet implementation class ProjectController
 */
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService = new ProjectServiceImpl();
	private Loggers log = new Loggers(EmployeeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

		try {
			switch (request.getParameter("action")) {
			case "projectMainPage":
				displayProjectMainPage(request, response);
				break;

			case "projectForm":
				displayProjectForm(request, response);
				break;

			case "editProject":
				editProject(request, response);
				break;

			case "deleteProject":
				deleteProject(request, response);
				break;

			case "restoreProject":
				restoreProject(request, response);
				break;

			case "assignEmployee":
				displayAssignEmployee(request, response);
				break;

			case "unAssignEmployee":
				displayUnassignEmployee(request, response);
				break;

			default:
				break;
			}
		} catch (UserDefinedException e) {

		}
	}

	/**
	 * We forward the request to project page along with project list
	 * of datas.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayProjectMainPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		request.setAttribute("projectList", projectService.getProjectForDisplay());
		try {
			request.getRequestDispatcher("projectPage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading projectPage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We redirect to project form page.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayProjectForm(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			response.sendRedirect("projectForm.jsp");
		} catch (IOException e) {
			log.logError("Error in loading projectForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward request to project form along with individual project
	 * datas.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("individualProject", 
				projectService.getIndividualProjectForDisplay(id));
		try {
			request.getRequestDispatcher("projectForm.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading projectForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We delete an project.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		int id = Integer.parseInt(request.getParameter("id"));
		projectService.deleteProject(id);
		displayProjectMainPage(request, response);
	}

	/**
	 * We restore an deleted project from the table.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void restoreProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		projectService.restoreProject(Integer.parseInt(request.getParameter("id")));
		displayProjectMainPage(request, response);
	}

	/**
	 * We forward the request to employee assign page along with 
	 * project datas.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayAssignEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		List<String> projectEmployee = new ArrayList<String>();
		List<Employee> employee = 
				employeeService.getAllEmployeeForDisplay();
		Project project = 
				projectService.getIndividualProjectForDisplay(id);
		projectEmployee.add(project.toString());

		for (Employee values : employee) {
			projectEmployee.add(values.toString());
		}
		request.setAttribute("project", projectEmployee);
		try {
			request.getRequestDispatcher("employeeAssign.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading employeeAssign.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to assigned employee page along 
	 * with project list.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayUnassignEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("projectList", 
				projectService.getIndividualProjectForDisplay(id));	
		try {
			request.getRequestDispatcher("assignedEmployeePage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading assignedEmployeePage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

		try {
			switch (request.getParameter("action")) {
			case "createProject":
				createProject(request, response);
				break;

			case "updateProject":
				updateProject(request, response);
				break;

			case "assignEmployeesToProject":
				assignEmployeeToProject(request, response);
				break;

			case "unAssignEmployeesToProject":
				unAssignEmployeeToProject(request, response);
				break;

			default:
				break;
			}
		} catch (UserDefinedException e) {

		}
	}

	/**
	 * We create an new project.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			String name = request.getParameter("name");
			LocalDate startDate = 
					LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = 
					LocalDate.parse(request.getParameter("endDate"));
			String status = request.getParameter("status");
			projectService.createProject(name, startDate, endDate, status);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from projectForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the projectService.java", e);
		}
		displayProjectMainPage(request, response);
	}

	/**
	 * We update the details of the project.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			String status = request.getParameter("status");
			projectService.updateProject(id, name, startDate, endDate, status);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from projectForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the projectService.java", e);
		}
		displayProjectMainPage(request, response);
	}

	/**
	 * We assign employees to an project.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assignEmployeeToProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			int projectId = Integer.parseInt(request.getParameter("id"));
			String[] employeeIdList = request.getParameterValues("employees");

			if (null != employeeIdList) {
				List<String> employeeId = Arrays.asList(employeeIdList);
				List<List<String>> idListForAssign = 
						projectService.getEmployeeIdList(projectId, employeeId);
				projectService.assignEmployeeToProject(projectId, 
						idListForAssign.get(0));
				request.setAttribute("projectList", 
						projectService.getIndividualProjectForDisplay(projectId));

				request.getRequestDispatcher("assignedEmployeePage.jsp").forward(request, 
						response);

			} else {
				displayAssignEmployee(request, response);
			} 
		} catch (ServletException | IOException e) {
			errorPage(request, response);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from projectForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the projectService.java", e);
		}
	}

	/**
	 * We unassign employees from an project.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unAssignEmployeeToProject(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			int projectId = Integer.parseInt(request.getParameter("id"));
			String[] employeeIdList = request.getParameterValues("employees");

			if (null != employeeIdList) {
				List<String> employeeId = Arrays.asList(employeeIdList);
				List<List<String>> idListForUnassign = 
						projectService.getEmployeeIdList(projectId, employeeId);
				projectService.unassignEmployeeFromProject(projectId, 
						idListForUnassign.get(1));
				request.setAttribute("projectList",
						projectService.getIndividualProjectForDisplay(projectId));

				request.getRequestDispatcher("assignedEmployeePage.jsp").forward(request, 
						response);

			} else {
				displayUnassignEmployee(request, response);
			} 
		} catch (ServletException | IOException e) {
			errorPage(request, response);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from projectForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the projectService.java", e);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void errorPage(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			response.sendRedirect("errorPage.jsp");
		} catch (IOException e) {

		}
	}
}