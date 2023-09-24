package com.finzly.bbcops.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.bbcops.entities.PaymentTransaction;
import com.finzly.bbcops.services.PaymentTransationService;

@CrossOrigin
@RestController
@RequestMapping("trasaction")
public class PaymentTransactionController {

	private final PaymentTransationService paymentTransationService;

	public PaymentTransactionController(PaymentTransationService paymentTransationService) {
		this.paymentTransationService = paymentTransationService;
	}

	//get all bill payment transactions
	@GetMapping("/get-all")
	public List<PaymentTransaction> getAllTrasactions() {
		return paymentTransationService.getAllTrasactions();
	}

	//get all bill payment transactions by customer ID
	@GetMapping("/get-by-customer/{customerId}")
	public ResponseEntity<List<PaymentTransaction>> getTrasactionsByCustomer(@PathVariable long customerId) {
		return paymentTransationService.getTrasactionsByCustomer(customerId);
	}

}
