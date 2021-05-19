package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeServiceImpl();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
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
		    
		case "logout":
			logout(request, response);
			break;
			
		default:
			break;
		}	
	}
	
	private void goToIndexPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("employeeList", 
				employeeService.getTodayEvents());
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeMainPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    request.setAttribute("employeeList",
				 employeeService.getAllEmployeeForDisplay());
		request.getRequestDispatcher("employeePage.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeForm(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("employeeForm.jsp");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressPage(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
		Employee employee = employeeService.getIndividualEmployeeForDisplay(id);
		request.setAttribute("addressList", employee.getAddresses());
		request.getRequestDispatcher("addressPage.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
		employeeService.deleteEmployee(id);
		goToEmployeeMainPage(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEmployeeFormForEdit(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
		request.setAttribute("individualEmployee", 
				employeeService.getIndividualEmployeeForDisplay(id));
		request.getRequestDispatcher("employeeForm.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressFormPage(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("employeeId", request.getParameter("employeeId"));
		request.getRequestDispatcher("addressForm.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAddressFormForEdit(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("address", 
				employeeService.getIndividualAddressForDisplay(addressId, employeeId));
		request.getRequestDispatcher("addressForm.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteIndividualAddress(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		String employeeId = request.getParameter("employeeId");
		employeeService.deleteIndividualAddress(employeeId, addressId);
		goToEmployeeMainPage(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToProjectAssignPage(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		ProjectService projectService = new ProjectServiceImpl();
		String employeeId = request.getParameter("employeeId");
		List<String> employeeProject = new ArrayList<String>();
		List<Project> project = projectService.getProjectForDisplay();
		Employee employee = employeeService.getIndividualEmployeeForDisplay(employeeId);
		employeeProject.add(employee.toString());
		
		for (Project values : project) {
			employeeProject.add(values.toString());
		}
		request.setAttribute("employee", employeeProject);
		request.getRequestDispatcher("projectAssign.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAssignedProjectsPage(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
		request.setAttribute("employeeList", 
				employeeService.getIndividualEmployeeForDisplay(id));
		request.getRequestDispatcher("assignedProjectPage.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		
		switch (request.getParameter("action")) {
			
		case "loginSubmit":
			goToHomePage(request, response);
		    break;
			
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
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToHomePage(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String mailId = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (mailId.equals("admin@ideas2it.com") && 
				password.equals("ideas2it")) {
			HttpSession session = request.getSession();
			session.setAttribute("mailId", mailId);
			request.setAttribute("employeeList", 
					employeeService.getTodayEvents());
			request.getRequestDispatcher("homePage.jsp").forward(request, response);
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
	    String id = request.getParameter("employeeId");
	    String name = request.getParameter("name");
	    String mobileNumber = request.getParameter("mobileNumber");
	    String mailId = request.getParameter("mailId");
	    LocalDate dateOfBirth = 
	    		LocalDate.parse(request.getParameter("dateOfBirth"));
	    LocalDate dateOfJoin = 
	    		LocalDate.parse(request.getParameter("dateOfJoin"));
	    double salary = 
	    		Double.parseDouble(request.getParameter("salary"));
	    int age = employeeService.getAge(dateOfBirth);
	    employeeService.createEmployee(id, name, dateOfJoin, dateOfBirth, 
	    		age, salary, mobileNumber, mailId);
	    createAddress(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createAddress(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
		String doorNumber = request.getParameter("doorNumber");
		String street = request.getParameter("street");
		String nagar = request.getParameter("nagar");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String country = request.getParameter("country");
		String addressType = request.getParameter("addressType");
		employeeService.addEmployeeAddress(id, doorNumber, street, 
				nagar, city, district, state, country, pincode, 
				addressType);
		goToEmployeeMainPage(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("employeeId");
	    String name = request.getParameter("name");
	    String mobileNumber = request.getParameter("mobileNumber");
	    String mailId = request.getParameter("mailId");
	    LocalDate dateOfBirth = 
	    		LocalDate.parse(request.getParameter("dateOfBirth"));
	    LocalDate dateOfJoin = 
	    		LocalDate.parse(request.getParameter("dateOfJoin"));
	    double salary = 
	    		Double.parseDouble(request.getParameter("salary"));
	    int age = employeeService.getAge(dateOfBirth);
	    employeeService.updateEmployee(id, name, dateOfJoin, dateOfBirth, 
	    		age, salary, mobileNumber, mailId);
	    updateAddress(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateAddress(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		String employeeId = request.getParameter("employeeId");
		String doorNumber = request.getParameter("doorNumber");
		String street = request.getParameter("street");
		String nagar = request.getParameter("nagar");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String country = request.getParameter("country");
		String addressType = request.getParameter("addressType");
		int id = Integer.parseInt(request.getParameter("addressId"));
		employeeService.updateAddress(employeeId, doorNumber, street, nagar, 
				city, district, state, country, pincode, addressType, id);
		goToAddressPage(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void restoreEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		employeeService.restoreEmployee(request.getParameter("id"));
		goToEmployeeMainPage(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void  assignProjectsToEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
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
 	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unAssignProjectsToEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
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
	}
}