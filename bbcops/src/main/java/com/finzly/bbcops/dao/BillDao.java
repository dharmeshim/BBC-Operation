package com.finzly.bbcops.dao;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbcops.entities.Bill;

@Repository
public class BillDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public BillDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean createNewBill(Bill bill) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(bill);
		transaction.commit();
		session.close();
		return true;
	}

	public Bill getBillById(long billId) {
		Session session = sessionFactory.openSession();
		Bill student = session.get(Bill.class, billId);
		session.close();
		return student;
	}

	public boolean updateBill(Bill bill) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(bill);
		tx.commit();
		session.close();
		return true;
	}

}
