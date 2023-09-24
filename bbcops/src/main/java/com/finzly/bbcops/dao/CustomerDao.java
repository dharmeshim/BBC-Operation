package com.finzly.bbcops.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbcops.entities.Bill;
import com.finzly.bbcops.entities.Customer;
import com.finzly.bbcops.util.mail.MailSenderService;

@Repository
public class CustomerDao {

	private final SessionFactory sessionFactory;
	private final MailSenderService mailService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

	@Autowired
	public CustomerDao(SessionFactory sessionFactory, MailSenderService mailService) {
		this.sessionFactory = sessionFactory;
		this.mailService = mailService;
	}

	public Customer getCustomerByPhoneNumber(String phoneNumber) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("telephone", phoneNumber));
		return (Customer) criteria.uniqueResult();

	}

	public Customer getCustomerById(long customerId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("customerId", customerId));
		return (Customer) criteria.uniqueResult();
	}

	public void saveCustomer(Customer customer) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(customer);
			mailService.sendMailAboutCustomerAdded(customer);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			logger.error("Exception : " + e);
			throw e;
		}

	}

	public boolean saveAllCustomers(List<Customer> customers) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			for (Customer customer : customers) {
				session.save(customer);
			}
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}

	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> customers = criteria.list();
		session.close();
		return customers;
	}

	public List<Bill> getUnpaidBillsByCustomerID(long customerId) {
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class, customerId);
		if (customer != null) {
			List<Bill> unpaidBills = customer.getBills().stream().filter(bill -> !bill.isPaid())
					.collect(Collectors.toList());
			session.close();
			return unpaidBills;
		}
		return null;
	}
}
