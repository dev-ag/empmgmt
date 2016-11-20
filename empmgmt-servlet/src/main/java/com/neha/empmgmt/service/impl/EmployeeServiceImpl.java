package com.neha.empmgmt.service.impl;

import java.util.List;

import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.dao.impl.EmployeeDaoImpl;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Employee findById(int id) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		return employeeDao.findById(id);
	}

	@Override
	public Employee findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

}
