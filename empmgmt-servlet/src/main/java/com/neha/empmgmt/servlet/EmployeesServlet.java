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

import org.apache.commons.lang3.StringUtils;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.DepartmentService;
import com.neha.empmgmt.service.EmployeeService;
import com.neha.empmgmt.service.impl.DepartmentServiceImpl;
import com.neha.empmgmt.service.impl.EmployeeServiceImpl;
import com.neha.empmgmt.validator.Validator;
import com.neha.empmgmt.validator.impl.EmployeeValidator;

@WebServlet(urlPatterns = "/employees.html")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeServiceImpl();
	private DepartmentService departmentService = new DepartmentServiceImpl();
	private Validator<Employee> employeeValidator = new EmployeeValidator();
	private List<Department> departments;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		System.out.println(servletConfig.getServletName() + " initialized..");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Service method called for " + request.getRequestURI());
		String action = request.getParameter("action");
		if (request.getMethod().equalsIgnoreCase(HttpMethod.GET)) {
			doGet(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.POST)) {
			if(StringUtils.isEmpty(action)){
				//do a POST if no action => Add
				doPost(request, response);
			} else if (action != null & action.equalsIgnoreCase("add")) {
				doPost(request, response);
			} else if (action != null & action.equalsIgnoreCase("edit")) {
				doPut(request, response);
			} else {
				throw new RuntimeException(request.getMethod() + " is not supported for action=" + action);
			}
		} else {
			throw new RuntimeException(request.getMethod() + " is not supported");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employees = employeeService.findAll();
		request.setAttribute("employees", employees);
		departments = departmentService.findAll();
		request.setAttribute("departments", departments);
		request.setAttribute("btnLabel", "Add Employee");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if (action != null) {
			departments = departmentService.findAll();
			request.setAttribute("departments", departments);

			if (action.equalsIgnoreCase("add")) {
				request.getRequestDispatcher("WEB-INF/views/employees.jsp?action=add").forward(request, response);
				return;
			} else if (action.equalsIgnoreCase("edit") && id != null) {
				Employee employeeToEdit = employeeService.findById(Integer.parseInt(id));
				request.setAttribute("employee", employeeToEdit);
				//showing btnLable
				request.setAttribute("btnLabel", "Update Employee");
				request.getRequestDispatcher("WEB-INF/views/employees.jsp?action=edit&id=" + id).forward(request,
						response);
				return;
			} else if (action.equalsIgnoreCase("delete") && id != null) {
				doDelete(request, response);
				return;
			}
		} else if (id != null) {
			Employee employee = employeeService.findById(Integer.parseInt(id));
			request.setAttribute("employee", employee);
			request.getRequestDispatcher("WEB-INF/views/employees.jsp").forward(request, response);
			return;
		} else {
			//findAll
			request.getRequestDispatcher("WEB-INF/views/employees.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		List<String> errors = employeeValidator.validate(employee);
		if (errors.size() > 0) {
			// This means validation had errors
			request.setAttribute("errors", errors);
			request.setAttribute("employee", employee);
			if (departments != null) {
				request.setAttribute("departments", departments);
			} else {
				departments = departmentService.findAll();
				request.setAttribute("departments", departments);
			}
			//populate list on the page
			List<Employee> employees = employeeService.findAll();
			request.setAttribute("employees", employees);
			request.getRequestDispatcher("WEB-INF/views/employees.jsp?action=add").forward(request, response);
			return;
		}
		isSaved = employeeService.save(employee);
		if (isSaved) {
			System.out.println("Employee " + employee.getFirstName() + " was added successfully");
		} else {
			System.out.println("Failed to add employee " + employee.getFirstName());
		}
		response.sendRedirect("employees.html?added=true");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		List<String> errors = employeeValidator.validate(employee);
		if (errors.size() > 0) {
			// This means validation had errors
			request.setAttribute("errors", errors);
			request.setAttribute("employee", employee);
			if (departments != null) {
				request.setAttribute("departments", departments);
			} else {
				departments = departmentService.findAll();
				request.setAttribute("departments", departments);
			}
			//populate list on the page
			List<Employee> employees = employeeService.findAll();
			request.setAttribute("employees", employees);
			request.getRequestDispatcher("WEB-INF/views/employees.jsp?action=edit&id=" + request.getParameter("id"))
					.forward(request, response);
			return;
		}
		isUpdated = employeeService.update(employee);
		if (isUpdated) {
			System.out.println("Employee " + employee.getFirstName() + " was updated successfully");
		} else {
			System.out.println("Failed to update employee " + employee.getFirstName());
		}
		response.sendRedirect("employees.html?updated=true");

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// Step 2: If id is present => deleteById
		if (id != null) {
			employeeService.deleteById(Integer.parseInt(id));
		} else {
			employeeService.deleteAll();
		}
		response.sendRedirect("employees.html?deleted=true");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet getting destroyed");
		super.destroy();
	}

}
