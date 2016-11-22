package com.neha.empmgmt.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

@WebServlet(urlPatterns = "/employees.html")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		System.out.println(servletConfig.getServletName()+" initialized..");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Service method called for "+request.getRequestURI());
		//Routing bases on the HttpMethod type and also based on a paramter called action that will be present in the url in case that is not form submit.
		// e.g. for a delete operation url = http://localhost:8080/employees.html?id=123&action=delete
		// e.g. for a update operation url = http://localhost:8080/employees.html?id=123&action=update
		// POST and PUT would normally be handled via form submit.
		if(request.getMethod().equalsIgnoreCase(HttpMethod.POST) || 
				(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("add"))){
			doPost(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.DELETE) || 
				(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("delete"))){
			doDelete(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT) || 
				(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("update"))){
			doDelete(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT)) {
			doGet(request, response);
		} else {
			if(request.getParameter("action") != null){
				throw new RuntimeException(request.getMethod() + " is not supported for action = "+request.getParameter("action"));
			}
			throw new RuntimeException(request.getMethod() + " is not supported");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Write logic to handle delete of an employee / employees
		//Check to see if id is present in the url = > deleteById
		//If id is not present => deleteAll
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Write logic to handle get employee / employees
		//Check to see if id is present in the url => getById
		//if id is not present => findAll
		//return appropriate response;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//write logic to post employee / employees
		//get all the employee parameters and make employee object
		//call save()
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//write logic to update employee / employees
		//get all employee parameters and make employee object
		//update()
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet getting destroyed");
		super.destroy();
	}

}
