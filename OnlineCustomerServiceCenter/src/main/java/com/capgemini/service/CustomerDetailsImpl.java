package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.repositories.CustomerRepository;


@Service
public class CustomerDetailsImpl implements CustomerDetails{

	@Autowired
	private CustomerRepository cusRepo;
	
	@Override
	public Optional<Customer> getCustomer(int cusid) {
		
		return cusRepo.findById(cusid);
	}

	@Override
	public Customer updateCustomer(Customer cus) {
		
		return cusRepo.save(cus);
	}

}
