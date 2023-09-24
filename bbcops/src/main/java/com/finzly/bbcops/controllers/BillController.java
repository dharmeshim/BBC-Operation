package com.finzly.bbcops.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.bbcops.entities.Bill;
import com.finzly.bbcops.services.BillService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/bill")
public class BillController {

	private final BillService billService;

	@Autowired
	public BillController(BillService billService) {
		this.billService = billService;
	}

	
	//create new bill
	@PostMapping("/create")
	public boolean createNewBill(@RequestBody Bill bill) {
		return billService.validateBillAndStore(bill);
	}

	
	//get bill by bill ID
	@GetMapping("/get-bill/{billId}")
	public Bill getCustomerByBill(@PathVariable long billId) {
		return billService.getBillById(billId);
	}

	//get all unpaid bills by customer id
	@GetMapping("/unpaid-bill/{customerId}")
	public HashMap<String, Object> getUnpaidBillsByCustomerID(@PathVariable long customerId) {
		return billService.getUnpaidBillsByCustomerID(customerId);
	}

	//mark bill paid by cash
	@PutMapping("/mark-payment-cash")
	public boolean markBillPaidByCash(@RequestBody Bill bill) {
		return billService.markBillPaidByCash(bill);
	}

	//add bill by CSV file
	@PostMapping("/upload-bill-file")
	public ResponseEntity<List<Bill>> addBillsByFile(@RequestParam("file") MultipartFile file) throws IOException {
		return billService.uploadBillsFromCSV(file);
	}
}
