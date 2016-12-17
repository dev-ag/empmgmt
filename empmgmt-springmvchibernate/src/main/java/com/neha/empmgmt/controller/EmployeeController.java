package com.neha.empmgmt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.DepartmentService;
import com.neha.empmgmt.service.EmployeeService;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		// Converts the date string coming from the view into the date object
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		System.out.println("Inside initBinder to format date...........................");

	}

	@RequestMapping(method = RequestMethod.GET)
	public String showAddFormAndListEmployees(ModelMap modelMap) {
		// Set ModelAttribute employee in Add Form
		modelMap.addAttribute("employee", new Employee());
		List<Employee> employees = employeeService.findAll();
		// For department dropdown in AddForm1
		List<Department> departments = departmentService.findAll();
		modelMap.addAttribute("departments", departments);
		modelMap.addAttribute("btnLabel", "Add Employee");

		// List of Employees
		modelMap.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute("employee") Employee employee, ModelMap modelMap) {
		if (employee.getId() == 0) {
			// add the employee
			employeeService.save(employee);
			return "redirect:/employees?added=true";
		} else {
			// update the employee
			employeeService.update(employee);
			return "redirect:/employees?updated=true";
		}
	}

	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String populateEditFormAndListDepartments(@RequestParam(required = true) String id, ModelMap modelMap) {
		// For Edit Form
		Employee employee = employeeService.findById(Integer.parseInt(id));
		modelMap.addAttribute("employee", employee);
		List<Department> departments = departmentService.findAll();
		modelMap.addAttribute("departments", departments);
		modelMap.addAttribute("btnLabel", "Update Employee");
		// List of Employees
		List<Employee> employees = employeeService.findAll();
		modelMap.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String removeById(@RequestParam(required = true) String id, ModelMap modelMap) {
		employeeService.deleteById(Integer.parseInt(id));
		// For ModelAttribute employee in Add Form
		modelMap.addAttribute("employee", new Employee());
		List<Employee> employees = employeeService.findAll();
		modelMap.addAttribute("employees", employees);
		return "redirect:/employees?deleted=true";
	}
}
