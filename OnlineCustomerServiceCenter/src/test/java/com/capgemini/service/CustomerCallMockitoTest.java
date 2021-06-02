package com.capgemini.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.capgemini.repositories.CustomerCallRepository;

@ExtendWith(SpringExtension.class)
public class CustomerCallMockitoTest {

	@InjectMocks 
	CustomerCallServiceImpl cusSer;
	
	@MockBean
	CustomerCallRepository cusRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
}
