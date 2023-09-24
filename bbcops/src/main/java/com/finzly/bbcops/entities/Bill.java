package com.finzly.bbcops.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long billId;
	private double unitConsumption;
	private LocalDate durationOfBill;
	private LocalDate billDueDate;
	private double billAmount;
	private boolean isPaid;
	private double amountForOnlinePay;
	private double amountForEarlyPay;
	private double amountForBothDiscount;

//	@JsonIgnore 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@JsonIgnore
	@OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
	private PaymentTransaction paymentTransaction;

	public Bill() {

	}

	public Bill(double unitConsumption, 
			LocalDate durationOfBill, 
			LocalDate billDueDate, 
			Customer customer) {
		super();
		this.unitConsumption = unitConsumption;
		this.durationOfBill = durationOfBill;
		this.billDueDate = billDueDate;
		this.customer = customer;
	}

	public Bill(long billId, 
			double unitConsumption, 
			LocalDate durationOfBill, 
			LocalDate billDueDate, 
			double billAmount,
			boolean isPaid, 
			double amountForOnlinePay, 
			double amountForEarlyPay, 
			double amountForBothDiscount,
			Customer customer, 
			PaymentTransaction paymentTransaction) {
		super();
		this.billId = billId;
		this.unitConsumption = unitConsumption;
		this.durationOfBill = durationOfBill;
		this.billDueDate = billDueDate;
		this.billAmount = billAmount;
		this.isPaid = isPaid;
		this.amountForOnlinePay = amountForOnlinePay;
		this.amountForEarlyPay = amountForEarlyPay;
		this.amountForBothDiscount = amountForBothDiscount;
		this.customer = customer;
		this.paymentTransaction = paymentTransaction;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public double getUnitConsumption() {
		return unitConsumption;
	}

	public void setUnitConsumption(double unitConsumption) {
		this.unitConsumption = unitConsumption;
	}

	public LocalDate getDurationOfBill() {
		return durationOfBill;
	}

	public void setDurationOfBill(LocalDate durationOfBill) {
		this.durationOfBill = durationOfBill;
	}

	public LocalDate getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(LocalDate billDueDate) {
		this.billDueDate = billDueDate;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public double getAmountForOnlinePay() {
		return amountForOnlinePay;
	}

	public void setAmountForOnlinePay(double amountForOnlinePay) {
		this.amountForOnlinePay = amountForOnlinePay;
	}

	public double getAmountForEarlyPay() {
		return amountForEarlyPay;
	}

	public void setAmountForEarlyPay(double amountForEarlyPay) {
		this.amountForEarlyPay = amountForEarlyPay;
	}

	public double getAmountForBothDiscount() {
		return amountForBothDiscount;
	}

	public void setAmountForBothDiscount(double amountForBothDiscount) {
		this.amountForBothDiscount = amountForBothDiscount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

}
