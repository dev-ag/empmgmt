package com.neha.empmgmt.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.database.DatabaseConnectionFactory;
import com.neha.empmgmt.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee findById(int id) {
		Employee employee = new Employee();
		String sql = "Select * from employee where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				employee.setId(rs.getInt("id"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setAge(rs.getInt("age"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setFullTime(rs.getBoolean("fulltime"));
				employee.setJoinDate(rs.getDate("joindate"));
				int departmentId = rs.getInt("department");

			}
			// TODO Wait until creation of DepartmentDaoImpl and findById
			// method in it.
			// Then use this method to get the department object here
			// employee.setDepartment(departmetDaoImpl.findById(departmentId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Employee findByEmail(String email) {
		Employee employee = new Employee();
		String sql = "Select * from employee where email = ?";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				employee.setId(rs.getInt("id"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setAge(rs.getInt("age"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setFullTime(rs.getBoolean("fulltime"));
				employee.setJoinDate(rs.getDate("joindate"));
				int departmentId = rs.getInt("department");

			}
			// TODO Wait until creation of DepartmentDaoImpl and findById
			// method in it.
			// Then use this method to get the department object here
			// employee.setDepartment(departmetDaoImpl.findById(departmentId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;

	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "Select * from employee";
		Statement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setAge(rs.getInt("age"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setFullTime(rs.getBoolean("fulltime"));
				employee.setJoinDate(rs.getDate("joindate"));
				int departmentId = rs.getInt("department");
				// TODO
				// employee.setDepartment(departmetDaoImpl.findById(departmentId));
				employees.add(employee);
			}
			// TODO Wait until creation of DepartmentDaoImpl and findById
			// method in it.
			// Then use this method to get the department object here
			// employee.setDepartment(departmetDaoImpl.findById(departmentId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;

	}

	@Override
	public boolean save(Employee employee) {

		// TODO: get departmentid from DepartmentDaoImpl based on the department
		// name findByName
		// int departmentId;
		String sql = "Insert into employee(firstname,lastname,email,age,salary,fulltime,joindate,department)"
				+ "values(? ,? ,? ,? ,? ,? ,? ,? )";
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getEmail());
			stmt.setInt(4, employee.getAge());
			stmt.setDouble(5, employee.getSalary());
			stmt.setBoolean(6, employee.isFullTime());
			stmt.setDate(7, (Date) employee.getJoinDate());
			// TODO :stmt.setInt(8, departmentId);
			if (stmt.executeUpdate(sql) > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "Delete from employee where id=?";
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
	public boolean update(Employee employee) {
		String sql = "Update employee "
				+ "set firstname=?,lastname=?,email=?,age=?,salary=?,fulltime=?,joindate=?,department=?"
				+ " where id =? ";
		PreparedStatement stmt = null;

		try {
			stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);

			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getEmail());
			stmt.setInt(4, employee.getAge());
			stmt.setDouble(5, employee.getSalary());
			stmt.setBoolean(6, employee.isFullTime());
			stmt.setDate(7, (Date) employee.getJoinDate());
			// TODO :stmt.setInt(8, departmentId);
			// TODO: get departmentid from DepartmentDaoImpl based on the
			// department
			// name findByName()
			stmt.setInt(9, employee.getId());
			if (stmt.executeUpdate(sql) > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
