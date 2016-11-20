package com.neha.empmgmt.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.database.DatabaseConnectionFactory;
import com.neha.empmgmt.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee findById(int id) {
		String sql = "Select * from employee where id = ?";
		PreparedStatement stmt = null;
		//STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      try{
	      stmt = DatabaseConnectionFactory.getConnection().prepareStatement(sql);
	      stmt.setInt(1, id);
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      
			while(rs.next()){
			     //Retrieve by column name
			     int age = rs.getInt("age");
			     String first = rs.getString("first");
			     String last = rs.getString("last");
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
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
