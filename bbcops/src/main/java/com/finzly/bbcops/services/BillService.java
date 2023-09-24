package com.finzly.bbcops.services;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import com.finzly.bbcops.dao.BillDao;
import com.finzly.bbcops.dao.PaymentTransactionDao;
import com.finzly.bbcops.entities.Bill;
import com.finzly.bbcops.entities.PaymentTransaction;
import com.finzly.bbcops.entities.Customer;

import com.finzly.bbcops.util.PaymentType;
import com.finzly.bbcops.util.constants.BillConstants;

@Service
public class BillService {

	private final BillDao billDao;
	private final PaymentTransactionDao paymentTransactionDao;
	private final CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);

	@Autowired
	public BillService(BillDao billDao, 
			CustomerService customerService, 
			PaymentTransactionDao paymentTransactionDao) {

		this.billDao = billDao;
		this.customerService = customerService;
		this.paymentTransactionDao = paymentTransactionDao;

	}

	public boolean validateBillAndStore(Bill bill) {
		long customerId = bill.getCustomer().getCustomerId();
		if (customerService.doesCustomerExist(customerId)) {

			Bill generatedBill = generateBillAmounts(bill);
			return billDao.createNewBill(generatedBill);
		} else {
			return false;
		}
	}

	private Bill generateBillAmounts(Bill bill) {

		double unitConsumed = bill.getUnitConsumption();
		double chargePerKw = BillConstants.BILLING_RATE_PER_KW;
		double billAmount = unitConsumed * chargePerKw;
		bill.setBillAmount(billAmount);
		bill.setAmountForEarlyPay(discountForEarlyPayment(billAmount));
		bill.setAmountForOnlinePay(discountForOnlinePayment(billAmount));
		bill.setAmountForBothDiscount(discountForBothOnlineAndEarlyPayment(billAmount));

		return bill;
	}

	private double applyDiscount(double totalBillAmount, double discountRate) {
		return totalBillAmount - (totalBillAmount * discountRate);
	}

	private double discountForEarlyPayment(double totalBillAmount) {
		return applyDiscount(totalBillAmount, BillConstants.EARLY_PAYMENT_DISCOUNT_RATE);
	}

	private double discountForOnlinePayment(double totalBillAmount) {
		return applyDiscount(totalBillAmount, BillConstants.ONLINE_PAYMENT_DISCOUNT_RATE);
	}

	private double discountForBothOnlineAndEarlyPayment(double totalBillAmount) {
		double discountedAmount = applyDiscount(totalBillAmount, BillConstants.EARLY_PAYMENT_DISCOUNT_RATE);
		return applyDiscount(discountedAmount, BillConstants.ONLINE_PAYMENT_DISCOUNT_RATE);
	}

	public Bill getBillById(long billId) {
		Bill bill = billDao.getBillById(billId);
		return bill;
	}

	public HashMap<String, Object> getUnpaidBillsByCustomerID(long customerId) {
		return customerService.getUnpaidBillsByCustomerID(customerId);
	}

	public boolean markBillPaidByCash(Bill bill) {
		LocalDate today = LocalDate.now();
		if (bill.getPaymentTransaction() == null) {
			PaymentTransaction paymentTransaction = new PaymentTransaction();
			paymentTransaction.setTransactionDate(today);
			paymentTransaction.setAmount(checkAndDiscountForEarlyPayment(bill));
			paymentTransaction.setPaymentType(PaymentType.CASH);
			paymentTransaction.setCustomer(bill.getCustomer());
			paymentTransaction.setBill(bill);

			paymentTransactionDao.savePaymentTransaction(paymentTransaction);
			bill.setPaymentTransaction(paymentTransaction);
		}

		bill.setPaid(true);
		billDao.updateBill(bill);
		return true;
	}

	private double checkAndDiscountForEarlyPayment(Bill bill) {

		LocalDate today = LocalDate.now();
		LocalDate billDueDate = bill.getBillDueDate();
		double totalAmount = bill.getBillAmount();

		if (today.isBefore(billDueDate) || today.equals(billDueDate)) {
			return discountForEarlyPayment(totalAmount);
		} else {
			return totalAmount;
		}
	}

	public ResponseEntity<List<Bill>> uploadBillsFromCSV(MultipartFile file) throws IOException {
		if (!isValidCsvFormat(file)) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			List<Bill> billsToAdd = parseCsvToBills(file.getInputStream());
			for (Bill bill : billsToAdd) {
				billDao.createNewBill(bill);
			}
			return new ResponseEntity<>(billsToAdd, HttpStatus.OK);
		}
	}

	private List<Bill> parseCsvToBills(InputStream inputStream) throws IOException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy"); // the date format

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

			List<Bill> bills = new ArrayList<>();

			int skipedBills = 0;
			for (CSVRecord record : csvParser) {
				try {
					double unitConsumption = Double.parseDouble(record.get("unitConsumption"));
					LocalDate durationOfBill = LocalDate.parse(record.get("durationOfBill"), dateFormatter);
					LocalDate billDueDate = LocalDate.parse(record.get("billDueDate"), dateFormatter);
					long customerId = Long.parseLong(record.get("customerId"));

					if (record.isSet("unitConsumption") && record.isSet("durationOfBill") && record.isSet("billDueDate")
							&& record.isSet("customerId")) {

						Customer customer = customerService.getCustomerById(customerId);
						if (customer != null) {
							Bill bill = new Bill(unitConsumption, durationOfBill, billDueDate, customer);
							bill = generateBillAmounts(bill);
							bills.add(bill);
						} else {
							skipedBills++;
						}
					} else {
						skipedBills++;
					}
				} catch (NumberFormatException | DateTimeParseException e) {
					skipedBills++;
				}
			}
			return bills;
		}
	}

	public boolean isValidCsvFormat(MultipartFile file) {
		return "text/csv".equals(file.getContentType());
	}

}
