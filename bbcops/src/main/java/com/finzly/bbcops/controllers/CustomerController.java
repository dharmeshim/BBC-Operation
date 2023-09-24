package com.finzly.bbcops.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.bbcops.entities.Customer;
import com.finzly.bbcops.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	//add new customer
	@PostMapping("/add-new")
	public ResponseEntity<Boolean> addNewCustomer(@RequestBody Customer customer) {
		return customerService.addNewCustomer(customer);
	}

	//add customers by CSV file
	@PostMapping("/upload-files")
	public ResponseEntity<List<Customer>> addCustomersByFile(@RequestParam("file") MultipartFile file)
			throws IOException {
		return customerService.uploadCustomersFromCSV(file);

	}

	//get all customers
	@GetMapping("/get-all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
}
