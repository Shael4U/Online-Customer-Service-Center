package com.capgemini.service;

import java.util.Optional;

import com.capgemini.entities.Customer;


public interface CustomerDetails {

	public Optional<Customer> getCustomer(int cusid);
	public Customer updateCustomer(Customer cus);
}
