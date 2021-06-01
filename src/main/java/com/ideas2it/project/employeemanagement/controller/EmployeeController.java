package com.ideas2it.project.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.projectmanagement.model.Project;
import com.ideas2it.project.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.exceptions.UserDefinedException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.project.projectmanagement.service.ProjectService;

/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = 
			new EmployeeServiceImpl();
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
			case "indexPage":
				goToIndexPage(request, response);
				break;

			case "employeeMainPage":
				goToEmployeeMainPage(request, response);
				break;

			case "employeeForm":
				goToEmployeeForm(request, response);
				break;

			case "viewAddress":
				goToAddressPage(request, response);
				break;

			case "deleteEmployee":
				deleteEmployee(request, response);
				break;

			case "editEmployee":
				goToEmployeeFormForEdit(request, response);
				break;

			case "addressForm":
				goToAddressFormPage(request, response);
				break;

			case "getAddressForEdit":
				goToAddressFormForEdit(request, response);
				break;

			case "deleteIndividualAddress":
				deleteIndividualAddress(request, response);
				break;

			case "projectAssignPage":
				goToProjectAssignPage(request, response);
				break;

			case "assignedProjects":
				goToAssignedProjectsPage(request, response);
				break;

			default:
				break;
			}	
		} catch (UserDefinedException e) {
            log.logError("Input doesnt match.");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToIndexPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		request.setAttribute("employeeList", 
				employeeService.getTodayEvents());
		try {
			request.getRequestDispatcher("homePage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading homePage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to the employee main page along with 
	 * an employee list of datas.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeMainPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		request.setAttribute("employeeList",
				employeeService.getAllEmployeeForDisplay());
		try {
			request.getRequestDispatcher("employeePage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading employeePage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We redirect back to employee form page.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeForm(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			response.sendRedirect("employeeForm.jsp");
		} catch (IOException e) {
			log.logError("Error in loading employeeForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to address page along with 
	 * address list of data.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		String id = request.getParameter("employeeId");
		Employee employee = 
				employeeService.getIndividualEmployeeForDisplay(id);
		request.setAttribute("addressList", employee.getAddresses());
		try {
			request.getRequestDispatcher("addressPage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading addressPage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We delete the employee data.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		String id = request.getParameter("employeeId");
		employeeService.deleteEmployee(id);
		goToEmployeeMainPage(request, response);
	}

	/**
	 * We forward the request to employee form page along with individual
	 * employee details.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeFormForEdit(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		String id = request.getParameter("employeeId");
		request.setAttribute("individualEmployee", 
				employeeService.getIndividualEmployeeForDisplay(id));
		try {
			request.getRequestDispatcher("employeeForm.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading employeeForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to address form page along with 
	 * employee id.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressFormPage(HttpServletRequest request, 
			HttpServletResponse response) {
		request.setAttribute("employeeId", 
				request.getParameter("employeeId"));
		try {
			request.getRequestDispatcher("addressForm.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading addressForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to address form page along with address
	 * values.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressFormForEdit(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("address", 
				employeeService.getIndividualAddressForDisplay(addressId, 
						employeeId));
		try {
			request.getRequestDispatcher("addressForm.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading addressForm.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We delete an individual address of an specific employee.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteIndividualAddress(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		String employeeId = request.getParameter("employeeId");
		employeeService.deleteIndividualAddress(employeeId, addressId);
		goToEmployeeMainPage(request, response);
	}

	/**
	 * We forward the request to project assign page along with
	 * employee data and project datas.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToProjectAssignPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		ProjectService projectService = new ProjectServiceImpl();
		String employeeId = request.getParameter("employeeId");
		List<String> employeeProject = new ArrayList<String>();
		List<Project> project = projectService.getProjectForDisplay();
		Employee employee = 
				employeeService.getIndividualEmployeeForDisplay(employeeId);
		employeeProject.add(employee.toString());

		for (Project values : project) {
			employeeProject.add(values.toString());
		}
		request.setAttribute("employee", employeeProject);
		try {
			request.getRequestDispatcher("projectAssign.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading projectAssign.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * We forward the request to assigned project page along with
	 * emmployee list.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAssignedProjectsPage(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		String id = request.getParameter("employeeId");
		request.setAttribute("employeeList", 
				employeeService.getIndividualEmployeeForDisplay(id));
		try {
			request.getRequestDispatcher("assignedProjectPage.jsp").forward(request, 
					response);
		} catch (ServletException | IOException e) {
			log.logError("Error in loading assignedProjectPage.jsp", e);
			errorPage(request, response);
		}
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

		try {
			switch (request.getParameter("action")) {
			case "createEmployee":
				createEmployee(request, response);
				break;

			case "updateEmployee":
				updateEmployee(request, response);
				break;

			case "createAddress":
				createAddress(request, response);
				break;

			case "updateAddress":
				updateAddress(request, response);
				break;

			case "restoreEmployee":
				restoreEmployee(request, response);
				break;

			case "assignProjectsToEmployee":
				assignProjectsToEmployee(request, response);
				break;

			case "unAssignProjectsToEmployee":
				unAssignProjectsToEmployee(request, response);
				break;

			default:
				break;
			} 
		} catch (UserDefinedException e) {
            log.logError("Input doesnt match.");
		}
	}

	/**
	 * We create an new employee.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			LocalDate dateOfBirth = 
					LocalDate.parse(request.getParameter("dateOfBirth"));
			LocalDate dateOfJoin = 
					LocalDate.parse(request.getParameter("dateOfJoin"));
			employeeService.createEmployee(request.getParameter("employeeId"), 
					request.getParameter("name"), 
					dateOfJoin, dateOfBirth, 
					employeeService.getAge(dateOfBirth), 
					Double.parseDouble(request.getParameter("salary")), 
					request.getParameter("mobileNumber"), 
					request.getParameter("mailId"),
					request.getParameter("mobileNumber") + 
					"@" + request.getParameter("employeeId"));
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from employeeForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);
		}
		createAddress(request, response);
	}

	/**
	 * We add address of an employee.
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createAddress(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			employeeService.addEmployeeAddress(request.getParameter("employeeId"), 
					request.getParameter("doorNumber"), request.getParameter("street"), 
					request.getParameter("nagar"), request.getParameter("city"), 
					request.getParameter("district"), request.getParameter("state"), 
					request.getParameter("country"), 
					Integer.parseInt(request.getParameter("pincode")), 
					request.getParameter("addressType"));
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from employeeForm.jsp or addressForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);
		}
		goToEmployeeMainPage(request, response);
	}

	/**
	 * We update details of an employee.
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws UserDefinedException  
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			LocalDate dateOfBirth = 
					LocalDate.parse(request.getParameter("dateOfBirth"));
			LocalDate dateOfJoin = 
					LocalDate.parse(request.getParameter("dateOfJoin"));
			employeeService.updateEmployee(request.getParameter("employeeId"), 
					request.getParameter("name"), dateOfJoin, dateOfBirth, 
					employeeService.getAge(dateOfBirth), 
					Double.parseDouble(request.getParameter("salary")), 
					request.getParameter("mobileNumber"), 
					request.getParameter("mailId"),
					request.getParameter("mobileNumber") + 
					"@" + request.getParameter("employeeId"));
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from employeeForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);
		}
		updateAddress(request, response);
	}

	/**
	 * We update address of an employee.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException  
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateAddress(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			employeeService.updateAddress(request.getParameter("employeeId"), 
					request.getParameter("doorNumber"), request.getParameter("street"), 
					request.getParameter("nagar"), request.getParameter("city"), 
					request.getParameter("district"), request.getParameter("state"), 
					request.getParameter("country"), 
					Integer.parseInt(request.getParameter("pincode")), 
					request.getParameter("addressType"), 
					Integer.parseInt(request.getParameter("addressId")));
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from employeeForm.jsp or addressForm.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);
		}
		goToEmployeeMainPage(request, response);
	}

	/**
	 * We restore deleted employee details.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void restoreEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			employeeService.restoreEmployee(request.getParameter("id"));
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from restoreEmployee.jsp."
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);
		}
		goToEmployeeMainPage(request, response);
	}

	/**
	 * We assign projects to an indidividual employee.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void  assignProjectsToEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			String employeeId = request.getParameter("employeeId");
			String[] projectIdList = request.getParameterValues("names");
			List<Integer> projectId = new ArrayList<Integer>();

			if (null != projectIdList) {

				for (int index = 0; index < projectIdList.length; index++) {
					projectId.add(Integer.parseInt(projectIdList[index]));
				}
				List<List<Integer>> idListForAssign = 
						employeeService.getAvailableProjectId(employeeId, projectId);
				employeeService.assignProjectToEmployee(employeeId, idListForAssign.get(0));
				request.setAttribute("employeeList", 
						employeeService.getIndividualEmployeeForDisplay(employeeId));

				request.getRequestDispatcher("assignedProjectPage.jsp").forward(request, response);
			} else {
				goToProjectAssignPage(request, response);
			} 
		} catch (ServletException | IOException e) {
			errorPage(request, response);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from "
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);	
		}
	}

	/**
	 * We unassign projects to an individual employee.
	 * 
	 * @param request
	 * @param response
	 * @throws UserDefinedException 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unAssignProjectsToEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws UserDefinedException {
		try {
			String employeeId = request.getParameter("employeeId");
			String[] projectIdList = request.getParameterValues("names");
			List<Integer> projectId = new ArrayList<Integer>();

			if (null != projectIdList) {

				for (int index = 0; index < projectIdList.length; index++) {
					projectId.add(Integer.parseInt(projectIdList[index]));
				}
				List<List<Integer>> idListForUnassign = 
						employeeService.getAvailableProjectId(employeeId, projectId);
				employeeService.unassignProjectFromEmployee(employeeId, idListForUnassign.get(1));
				request.setAttribute("employeeList", 
						employeeService.getIndividualEmployeeForDisplay(employeeId));

				request.getRequestDispatcher("assignedProjectPage.jsp").forward(request, response);

			} else {
				goToAssignedProjectsPage(request, response);
			}
		} catch (ServletException | IOException e) {
			errorPage(request, response);
		} catch (NumberFormatException e) {
			log.logError("There is an issue in getting value from "
					+ "The may be issue in parameter variable or the input type data may differ.", e);
		} catch (InputMismatchException e) {
			log.logError("There is an issue in passing values to the employeeService.java", e);	
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