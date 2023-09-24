package com.finzly.bbcops.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finzly.bbcops.dao.PaymentTransactionDao;
import com.finzly.bbcops.entities.Customer;
import com.finzly.bbcops.entities.PaymentTransaction;

@Service
public class PaymentTransationService {

	private final PaymentTransactionDao paymentTransactionDao;
	private final CustomerService customerService;

	public PaymentTransationService(PaymentTransactionDao 
			paymentTransactionDao, 
			CustomerService customerService) {
		
		this.paymentTransactionDao = paymentTransactionDao;
		this.customerService = customerService;
	}

	public List<PaymentTransaction> getAllTrasactions() {
		return paymentTransactionDao.getAllTrasactions();
	}

	public ResponseEntity<List<PaymentTransaction>> getTrasactionsByCustomer(long customerId) {
		if (customerService.doesCustomerExist(customerId)) {
			List<PaymentTransaction> transactions = paymentTransactionDao.getTransactionsByCustomer(customerId);

			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
