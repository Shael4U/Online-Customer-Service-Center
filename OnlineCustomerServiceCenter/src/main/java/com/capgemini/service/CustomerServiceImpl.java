package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Issue;
import com.capgemini.entities.Login;
import com.capgemini.entities.UserType;
import com.capgemini.exception.DuplicateCustomerException;
import com.capgemini.exception.InvalidCredentialException;
import com.capgemini.repositories.CustomerEmailRepository;
import com.capgemini.repositories.CustomerRepository;
import com.capgemini.repositories.IssueRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerEmailRepository cusRepo;
	
	@Autowired
	private CustomerRepository cusRep;
	
	@Autowired
	private IssueRepository issRepo;
	
	Issue issue;
	Customer cus;
	
	@Override
	public String login(Login login) throws InvalidCredentialException {
		
		cus = (Customer) cusRepo.findByEmail(login.getUsername());
		if(cus == null)
		{
			throw new InvalidCredentialException("UserName is Invalid : "+login.getUsername()); 
		}
		else if(login.getUsername().equals(cus.getEmail()) && login.getPassword().equals(cus.getPassword()) && login.getType().equals(UserType.CUSTOMER.toString()))
		{
			return "Welcome to Our Portal";
		}
		else
		{
			throw new InvalidCredentialException("Failed to Login with"+" "+login.getUsername()+" "+"or Password "+"or UserType Case is Invalid");
		}
	}


	@Override
	public String registerCustomer(Customer customer) throws DuplicateCustomerException {
		
		cus = (Customer) cusRepo.findByEmail(customer.getEmail());
		if(cus==null)
		{
			 cusRepo.save(customer);
			 return "Successfully Registered";
		}
		else
		{
			throw new DuplicateCustomerException("User name already exists"+" "+customer.getEmail());
		}
		
	}

	@Override
	public Optional<Issue> viewIssueById(int issueId) {
		
		return issRepo.findById(issueId);
	}

	@Override
	public Issue raiseIssue(Issue iss, String email) {
		
		cus = (Customer) cusRepo.findByEmail(email);
		if(cus!=null)
		{
			iss.addCustomer(cus);
			issRepo.save(iss);
			return iss;
		}
		else
		{
			return new Issue();
		}
		
	}
	
	@Override
	public Optional<Issue> reopenIssue(int issueId) {
		
		Optional<Issue> issue = issRepo.findById(issueId);
		return issue;//Contact Operator
		
	}

	@Override
	public List<Issue> viewAllIssues() {
		
		return issRepo.findAll();
	}

	@Override
	public String changePassword(Login login) {
		
		cus = (Customer) cusRepo.findByEmail(login.getUsername());
		if(login.getUsername().equals(cus.getEmail()))
		{
			cus.setPassword(login.getPassword());
			cusRepo.save(cus);
			return "Successfully Changed";
		}
		else
		{
			return "Not Valid User";
		}
	}

	@Override
	public String forgotPassword(String email) {
		
		cus = (Customer) cusRepo.findByEmail(email);
		if(email.equals(cus.getEmail()))
		{
			return "Your Old Password is : "+cus.getPassword();
		}
		return "Not Valid User";
	}

	@Override
	public Optional<Customer> emailPassword(int customerId) {
		
		return cusRep.findById(customerId);
	}

	
}
