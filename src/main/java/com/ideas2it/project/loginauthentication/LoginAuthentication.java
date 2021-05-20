package com.ideas2it.project.loginauthentication;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * Servlet implementation class LoginAuthentication
 */
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginAuthentication() {
        // TODO Auto-generated constructor stub
    }

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
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

}
