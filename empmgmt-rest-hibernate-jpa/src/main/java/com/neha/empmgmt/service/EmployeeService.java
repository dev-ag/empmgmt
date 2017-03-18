package com.neha.empmgmt.service;

import java.util.List;

import com.neha.empmgmt.model.Employee;

public interface EmployeeService {
	Employee findById(int id);

	Employee findByEmail(String email);

	List<Employee> findAll();

	boolean save(Employee employee);

	boolean deleteById(int id);

	boolean update(Employee employee);

	boolean deleteAll();
}
