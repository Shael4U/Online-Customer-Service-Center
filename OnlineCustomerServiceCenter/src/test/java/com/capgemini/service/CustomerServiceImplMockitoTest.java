package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Issue;
import com.capgemini.entities.Login;
import com.capgemini.exception.DuplicateCustomerException;
import com.capgemini.repositories.CustomerEmailRepository;
import com.capgemini.repositories.IssueRepository;



@ExtendWith(SpringExtension.class)
public class CustomerServiceImplMockitoTest {
	
	@InjectMocks 
	CustomerServiceImpl cusSer;
	
	@MockBean
	CustomerEmailRepository cusRepo;
	
	@MockBean
	IssueRepository issRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testLogin()
	{
		Customer cus = new Customer(101,"Parvathi","Eswar","Parvathi@gmail.com","1234","7980878675","TPT");
		Login log = new Login("Parvathi@gmail.com","1234","CUSTOMER");
		Mockito.when(cusRepo.findByEmail("Parvathi@gmail.com")).thenReturn(cus);
		String persistedlog = cusSer.login(log);
		assertEquals("Welcome to Our Portal", persistedlog.toString());
	}
	
	@Test
	void testRegisterCustomer()
	{
		Customer cus = new Customer(102,"Karan","Eswar","Karan@gmail.com","12345","7980878667","PTR");
		Mockito.when(cusRepo.save(cus)).thenReturn(cus);
		String persistedlog = cusSer.registerCustomer(cus);
		assertEquals("Successfully Registered",persistedlog.toString());	
	}
	
	@Test
	void testIssueRaise()
	{
		Customer cus = new Customer(101,"Parvathi","Eswar","Parvathi@gmail.com","1234","7980878675","TPT");
		Issue iss = new Issue(201,"Network","Not Getting","Active");
		Mockito.when(cusRepo.findByEmail("Parvathi@gmail.com")).thenReturn(cus);
		Mockito.when(issRepo.save(iss)).thenReturn(iss);
		Issue persistedlog = cusSer.raiseIssue(iss, "Parvathi@gmail.com");
		assertEquals(201, persistedlog.getIssueId());
		assertEquals("Network", persistedlog.getIssueType());
		assertEquals("Not Getting", persistedlog.getDescription());
		assertEquals("Active", persistedlog.getIssueStatus());
	}
	
	@Test
	void testGetAllIssues()
	{
		Issue iss = new Issue(201,"Network","Not Getting","Active");
		Issue iss1 = new Issue(202,"Wifi","Not Getting","Active");
		List<Issue> issList = new ArrayList<Issue>();
		issList.add(iss);
		issList.add(iss1);
		Mockito.when(issRepo.findAll()).thenReturn(issList);
		List<Issue> issue = cusSer.viewAllIssues();
		assertEquals(2,issList.size());
	}
	
	@Test
	void testChangePassword()
	{
		Customer cus = new Customer(101,"Parvathi","Eswar","Parvathi@gmail.com","1234","7980878675","TPT");
		Mockito.when(cusRepo.findByEmail("Parvathi@gmail.com")).thenReturn(cus);
		cus.setPassword("123");
		Mockito.when(cusRepo.save(cus)).thenReturn(cus);
		Login log = new Login("Parvathi@gmail.com","1234","CUSTOMER");
		String persistedlog = cusSer.changePassword(log);
		assertEquals("Successfully Changed", persistedlog.toString());
	}
	
	@Test
	void testForgotPassword()
	{
		Customer cus = new Customer(101,"Sai","Eswar","Sai@gmail.com","1234","7980878675","TPT");
		Mockito.when(cusRepo.findByEmail("Sai@gmail.com")).thenReturn(cus);
		String persistedlog = cusSer.forgotPassword(cus.getEmail());
		assertEquals("Your Old Password is : "+cus.getPassword(), persistedlog.toString());
	}
	
}
