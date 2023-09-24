package com.finzly.bbcops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbcops.entities.Customer;
import com.finzly.bbcops.entities.PaymentTransaction;

@Repository
public class PaymentTransactionDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public PaymentTransactionDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean savePaymentTransaction(PaymentTransaction paymentTransaction) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(paymentTransaction);
		transaction.commit();
		session.close();
		return true;

	}

	public List<PaymentTransaction> getAllTrasactions() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PaymentTransaction.class);
		List<PaymentTransaction> trasactions = criteria.list();
		session.close();
		return trasactions;
	}

	public List<PaymentTransaction> getTransactionsByCustomer(long customerId) {
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(PaymentTransaction.class)
					.createAlias("customer", "c")
					.add(Restrictions.eq("c.customerId", customerId));
			return criteria.list();
		} finally {
			session.close();
		}
	}

}
