package com.neha.empmgmt.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.database.DatabaseConnectionFactory;
import com.neha.empmgmt.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public Department findById(int id) {
		Department department = new Department();
		String sql = "Select * from department where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public Department findByName(String name) {
		Department department = new Department();
		String sql = "Select * from department where name = ?";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public List<Department> findAll() {
		List<Department> departments = new ArrayList<Department>();
		String sql = "Select * from department";
		Statement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public boolean save(Department department) {
		String sql = "Insert into department(name) values(?)";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, department.getName());
			if (stmt.executeUpdate(sql) > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "Delete from department where id=?";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			if (stmt.executeUpdate(sql) > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Department department) {
		String sql = "Update department set name=? where id =? ";
		PreparedStatement stmt = null;

		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);

			stmt.setString(1, department.getName());
			stmt.setInt(2, department.getId());
			if (stmt.executeUpdate(sql) > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
