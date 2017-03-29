package com.neha.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.DepartmentService;
import com.neha.empmgmt.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Employee findByID(@PathVariable int id) {
		return employeeService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public String save(@RequestBody Employee employee) {
		boolean created = employeeService.save(employee);
		if (created) {
			return "Employee created successfully";
		}
		return "Could not save employee";
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String update(@RequestBody Employee employee) {
		boolean created = employeeService.update(employee);
		if (created) {
			return "Employee updated successfully";
		}
		return "Could not update employee";
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteByID(@PathVariable int id) {
		boolean deleted = employeeService.deleteById(id);
		if (deleted) {
			return "Employee deleted successfully";
		}
		return "Could not delete employee";
	}
}

