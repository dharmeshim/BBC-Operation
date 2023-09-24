package com.finzly.bbcops.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "customer_id", 
	unique = true, 
	nullable = false)
	private long customerId;

	@Column(name = "name", 
			nullable = false)
	private String name;
	private String email;
	private String telephone;
	private String address;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", 
				cascade = CascadeType.ALL, 
				fetch = FetchType.LAZY)
	private List<Bill> bills;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<PaymentTransaction> paymentTransactions;

	public Customer() {
	}

	public Customer(long customerId, 
			String name, 
			String email, 
			String telephone, 
			String address, 
			List<Bill> bills,
			List<PaymentTransaction> paymentTransactions) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
		this.bills = bills;
		this.paymentTransactions = paymentTransactions;
	}

	public Customer(String name, 
			String email, 
			String telephone, 
			String address) {
		super();
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public List<PaymentTransaction> getPaymentTransactions() {
		return paymentTransactions;
	}

	public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
		this.paymentTransactions = paymentTransactions;
	}

}
