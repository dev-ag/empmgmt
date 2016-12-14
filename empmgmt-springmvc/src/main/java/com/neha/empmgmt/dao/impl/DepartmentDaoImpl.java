package com.neha.empmgmt.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.dao.mapper.DepartmentMapper;
import com.neha.empmgmt.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Department findById(int id) {
		String sql = "Select * from department where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new DepartmentMapper());
	}

	@Override
	public Department findByName(String name) {
		String sql = "Select * from department where name = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, new DepartmentMapper());
	}

	@Override
	public List<Department> findAll() {
		String sql = "Select * from department";
		return jdbcTemplate.query(sql, new DepartmentMapper());
	}

	@Override
	public boolean save(Department department) {
		String sql = "Insert into department(name) values(?)";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { department.getName() });
		if (noOfRows > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteById(int id) {
		String sql = "Delete from department where id=?";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { id });
		if (noOfRows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Department department) {
		String sql = "Update department set name=? where id =? ";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { department.getName(), department.getId() });
		if (noOfRows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAll() {
		String sql = "Delete from department";
		int noOfRows = jdbcTemplate.update(sql);
		if (noOfRows > 0) {
			return true;
		}
		return false;
	}

}
