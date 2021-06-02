package com.capgemini.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import com.capgemini.entities.Customer;
import com.capgemini.repositories.CustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {

	
	@InjectMocks 
	CustomerDetailsImpl cusSer;
	
	@MockBean
	CustomerRepository cusRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testUpdateCustomer()
	{
		Customer cus = new Customer(102,"Chery","Eswar","Chery@gmail.com","1234","7980878675","TPT");
		Mockito.when(cusRepo.findById(102)).thenReturn(Optional.of(cus));
		cus.setPassword("123");		
		Mockito.when(cusRepo.save(cus)).thenReturn(cus);
		Customer persistedcus = cusSer.updateCustomer(cus);
		assertEquals(102,persistedcus.getCustomerId());
		assertEquals("Chery",persistedcus.getFirstName());
		assertEquals("Eswar",persistedcus.getLastName());
		assertEquals("Chery@gmail.com",persistedcus.getEmail());
		assertEquals("123",persistedcus.getPassword());
		assertEquals("7980878675",persistedcus.getMobile());
		assertEquals("TPT",persistedcus.getCity());
	}
}
