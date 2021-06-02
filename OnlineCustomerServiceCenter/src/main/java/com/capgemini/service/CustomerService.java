package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.exception.DuplicateCustomerException;
import com.capgemini.exception.InvalidCredentialException;
import com.capgemini.entities.Customer;
import com.capgemini.entities.Issue;
import com.capgemini.entities.Login;

public interface CustomerService {

	public String login(Login login)throws InvalidCredentialException;
	public String registerCustomer(Customer customer)throws DuplicateCustomerException;
	public Optional<Issue> viewIssueById(int issueId);
	public Issue raiseIssue(Issue iss, String email);
	public Optional<Issue> reopenIssue(int issueId);
	public List<Issue> viewAllIssues();
	public String changePassword(Login login);
	public String forgotPassword(String customerId);
	public Optional<Customer> emailPassword(int customerId);
}
