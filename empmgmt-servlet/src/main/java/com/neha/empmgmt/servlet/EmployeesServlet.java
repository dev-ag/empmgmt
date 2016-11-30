package com.neha.empmgmt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

@WebServlet(urlPatterns = "/employees.html")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeesServlet() {
		super(); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("add") != null && request.getParameter("add").equalsIgnoreCase("add")) {
			request.getRequestDispatcher("WEB-INF/views/addemployee.jsp").forward(request, response);
		}
		// TODO : More if statements for delete, update and save
	}

}
