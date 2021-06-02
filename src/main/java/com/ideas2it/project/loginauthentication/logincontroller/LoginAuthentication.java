package com.ideas2it.project.loginauthentication.logincontroller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.exceptions.UserDefinedException;
import com.ideas2it.project.logger.Loggers;

/**
 * Servlet implementation class LoginAuthentication
 */
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Loggers log = new Loggers(LoginAuthentication.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("mailId");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		String mailId = request.getParameter("email");
		String password = request.getParameter("password");

		if (mailId.equals("admin@ideas2it.com") && 
				password.equals("ideas2it")) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("mailId", mailId);
				log.logInfo("Admin login is successful.");
				request.setAttribute("employeeList", 
						employeeService.getTodayEvents());
				request.getRequestDispatcher("homePage.jsp").forward(request, response);
			} catch (ServletException | IOException | UserDefinedException e) {
				log.logError("Error in opening home page.", e);
			}	
		} else {
			log.logInfo("The given mail Id and password are incorrect.");
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				log.logError("Error in opening login page.", e);
			}
		}
	}

}
