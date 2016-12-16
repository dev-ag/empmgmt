package com.neha.empmgmt.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.model.Department;

@Repository
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Department findById(int id) {
		return (Department)getHibernateTemplate().find("from department where id=?", id);
	}

	@Override
	public Department findByName(String name) {
		return (Department)getHibernateTemplate().find("from department where name = ?", name);
	}

	@Override
	public List<Department> findAll() {
		List<Department> departments=(List<Department>) getHibernateTemplate().find("from department");
		return departments;
	}

	@Override
	public boolean save(Department department) {
		/*String sql = "Insert into department(name) values(?)";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { department.getName() });
		if (noOfRows > 0) {
			return true;
		}*/
		return false;

	}

	@Override
	public boolean deleteById(int id) {
		/*String sql = "Delete from department where id=?";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { id });
		if (noOfRows > 0) {
			return true;
		}*/
		return false;
	}

	@Override
	public boolean update(Department department) {
		/*String sql = "Update department set name=? where id =? ";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { department.getName(), department.getId() });
		if (noOfRows > 0) {
			return true;
		}*/
		return false;
	}

	@Override
	public boolean deleteAll() {
		/*String sql = "Delete from department";
		int noOfRows = jdbcTemplate.update(sql);
		if (noOfRows > 0) {
			return true;
		}*/
		return false;
	}

}
