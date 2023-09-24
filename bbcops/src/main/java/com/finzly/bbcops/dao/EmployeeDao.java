package com.finzly.bbcops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbcops.entities.Employee;

@Repository
public class EmployeeDao {

	private final SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

	@Autowired
	public EmployeeDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Employee> getEmployees() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = criteria.list();
		session.close();
		return employees;
	}

	public Employee getEmployeeById(long employeeId) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, employeeId);
		session.close();
		return employee;
	}

	public String updateEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employee);
		tx.commit();
		session.close();
		return "Employee updated in the table successfully.";

	}

	public boolean doesEmployeeExist(long employeeId) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, employeeId);
		session.close();
		return employee != null;
	}

}
