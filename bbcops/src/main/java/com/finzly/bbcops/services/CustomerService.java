package com.finzly.bbcops.services;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.bbcops.dao.CustomerDao;
import com.finzly.bbcops.entities.Bill;
import com.finzly.bbcops.entities.Customer;

@Service
public class CustomerService {

	private final CustomerDao customerDao;
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public ResponseEntity<Boolean> addNewCustomer(Customer customer) {
		if (!doesCustomerExist(customer)) {
			customerDao.saveCustomer(customer);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.ALREADY_REPORTED);
		}
	}

	public ResponseEntity<List<Customer>> uploadCustomersFromCSV(MultipartFile file) throws IOException {
		if (isValidCsvFormat(file)) {
			List<Customer> customersToAdd = parseCsvToCustomers(file.getInputStream());
			saveAllCustomers(customersToAdd);

			return new ResponseEntity<>(customersToAdd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	private List<Customer> parseCsvToCustomers(InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
						.withTrim())) {

			List<Customer> customers = new ArrayList<>();
			for (CSVRecord csvRecord : csvParser) {
				String name = csvRecord.get("Customer Name");
				String email = csvRecord.get("email");
				String telephone = csvRecord.get("telephone");
				String address = csvRecord.get("address");

				Customer customer = new Customer(name, email, telephone, address);
				customers.add(customer);
			}
			return customers;
		}
	}

	private List<Customer> saveAllCustomers(List<Customer> customers) {
		List<Customer> notSavedCustomers = new ArrayList<>();

		for (Customer customer : customers) {
			if (!doesCustomerExist(customer)) {
				customerDao.saveCustomer(customer);
			} else {
				notSavedCustomers.add(customer);
			}
		}
		return notSavedCustomers;
	}
	

	public boolean doesCustomerExist(Customer customer) {
		String customerPhoneNumber = customer.getTelephone();
		Customer customerInTable = customerDao.getCustomerByPhoneNumber(customerPhoneNumber);
		return customerInTable != null;
	}

	public boolean isValidCsvFormat(MultipartFile file) {
		return "text/csv".equals(file.getContentType());
	}

	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	public HashMap<String, Object> getUnpaidBillsByCustomerID(long customerId) {
		HashMap<String, Object> result = new HashMap<>();

		if (doesCustomerExist(customerId)) {
			List<Bill> unpaidBills = customerDao.getUnpaidBillsByCustomerID(customerId);
			result.put("unpaidBills", unpaidBills);
			result.put("customerExists", true);
		} else {
			result.put("unpaidBills", null);
			result.put("customerExists", false);
		}
		return result;
	}

	public boolean doesCustomerExist(long customerId) {
		Customer customerInTable = customerDao.getCustomerById(customerId);
		return customerInTable != null;
	}

	public Customer getCustomerById(long customerId) {
		return customerDao.getCustomerById(customerId);
	}
}
