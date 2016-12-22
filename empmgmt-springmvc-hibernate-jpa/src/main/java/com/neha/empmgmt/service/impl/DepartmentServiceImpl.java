package com.neha.empmgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

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

	@Override
	public boolean deleteAll() {
		return departmentDao.deleteAll();
	}
}
