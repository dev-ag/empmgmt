package com.neha.empmgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	@Override
	public Employee findByEmail(String email) {
		return employeeDao.findByEmail(email);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public boolean save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public boolean deleteById(int id) {
		return employeeDao.deleteById(id);
	}

	@Override
	public boolean update(Employee employee) {
		return employeeDao.update(employee);
	}

	@Override
	public boolean deleteAll() {
		return employeeDao.deleteAll();
	}
}
