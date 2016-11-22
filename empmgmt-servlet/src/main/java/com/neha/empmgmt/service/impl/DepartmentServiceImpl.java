package com.neha.empmgmt.service.impl;

import java.util.List;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.dao.impl.DepartmentDaoImpl;
import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImpl();

	@Override
	public Department findById(int id) {
		return departmentDao.findById(id);
	}

	@Override
	public Department findByName(String name) {
		return departmentDao.findByName(name);
	}

	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public boolean save(Department department) {
		return departmentDao.save(department);
	}

	@Override
	public boolean deleteById(int id) {
		return departmentDao.deleteById(id);
	}

	@Override
	public boolean update(Department department) {
		return departmentDao.update(department);
	}
}
