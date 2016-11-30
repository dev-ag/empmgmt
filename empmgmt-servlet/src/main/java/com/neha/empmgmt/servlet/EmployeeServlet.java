package com.neha.empmgmt.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.EmployeeService;
import com.neha.empmgmt.service.impl.EmployeeServiceImpl;

@WebServlet(urlPatterns = "/employee.html")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		System.out.println(servletConfig.getServletName() + " initialized..");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Service method called for " + request.getRequestURI());
		// Routing bases on the HttpMethod type and also based on a paramter
		// called action that will be present in the url in case that is not
		// form submit.
		// e.g. for a delete operation url =
		// http://localhost:8080/employees.html?id=123&action=delete
		// e.g. for a update operation url =
		// http://localhost:8080/employees.html?id=123&action=update
		// POST and PUT would normally be handled via form submit.
		if (request.getMethod().equalsIgnoreCase(HttpMethod.POST)
				|| (request.getParameter("add") != null && request.getParameter("add").equalsIgnoreCase("Add"))) {
			doPost(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.DELETE) || (request.getParameter("action") != null
				&& request.getParameter("action").equalsIgnoreCase("delete"))) {
			doDelete(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT) || (request.getParameter("action") != null
				&& request.getParameter("action").equalsIgnoreCase("update"))) {
			doDelete(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT)) {
			doGet(request, response);
		} else {
			if (request.getParameter("action") != null) {
				throw new RuntimeException(
						request.getMethod() + " is not supported for action = " + request.getParameter("action"));
			}
			throw new RuntimeException(request.getMethod() + " is not supported");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Write logic to handle delete of an employee / employees
		// Check to see if id is present in the url = > deleteById
		// If id is not present => deleteAll
		Boolean isDeleted = false;
		if (request.getParameter("id") != null) {
			isDeleted = employeeService.deleteById(Integer.parseInt(request.getParameter("id")));

		} else {
			// TODO: instead of deleteAll, display the message that employee not
			// found/could not delete
			isDeleted = employeeService.deleteAll();
		}
		// TODO: Display message in UI that the EmployeeWithID/Employees deleted
		// successfully
		// OR show the updated result by doing a findAll().
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Write logic to handle get employee / employees
		// Check to see if id is present in the url => getById
		// if id is not present => findAll
		// return appropriate response;

		if (request.getParameter("id") != null) {
			Employee employee = employeeService.findById(Integer.parseInt(request.getParameter("id")));
			// TODO :return appropriate response;
		} else {
			List<Employee> employees = employeeService.findAll();
			request.setAttribute("employee", employees);
			request.getRequestDispatcher("WEB-INF/views/employeelist.jsp").forward(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// write logic to post employee / employees
		// get all the employee parameters and make employee object
		// call save()
		Boolean isSaved = false;
		Employee employee = new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setEmail(request.getParameter("email"));
		employee.setAge(Integer.parseInt(request.getParameter("age")));
		employee.setSalary(Double.parseDouble(request.getParameter("salary")));
		employee.setFullTime(Boolean.parseBoolean(request.getParameter("fulltime")));
		employee.setJoinDate(Date.valueOf(request.getParameter("joindate")));
		Department department = new Department();
		department.setName(request.getParameter("department"));
		employee.setDepartment(department);
		isSaved = employeeService.save(employee);
		if(isSaved){
			System.out.println("Successfully saved the employee" + request.getParameter("firstname")+ "  to the database......");
			this.doGet(request, response);
		}
		else{
			System.out.println("Failed to save the employee........");
		}
		// TODO: Display message in UI that the Employee is saved successfully
		// OR show the saved result by doing a findAll().
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// write logic to update employee / employees
		// get all employee parameters and make employee object
		// update()
		Boolean isUpdated = false;
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(request.getParameter("id")));
		employee.setFirstName(request.getParameter("firstname"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setEmail(request.getParameter("email"));
		employee.setAge(Integer.parseInt(request.getParameter("age")));
		employee.setSalary(Double.parseDouble(request.getParameter("salary")));
		employee.setFullTime(Boolean.parseBoolean(request.getParameter("fulltime")));
		employee.setJoinDate(Date.valueOf(request.getParameter("joindate")));
		Department department = new Department();
		department.setName(request.getParameter("department"));
		employee.setDepartment(department);
		isUpdated = employeeService.update(employee);

		// TODO: Display message in UI that the Employee is updated successfully
		// OR show the updated result by doing a findAll().
	}

	@Override
	public void destroy() {
		System.out.println("Servlet getting destroyed");
		super.destroy();
	}

}
