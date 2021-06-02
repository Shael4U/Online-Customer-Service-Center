package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Call;
import com.capgemini.repositories.CustomerCallRepository;

@Service
public class CustomerCallServiceImpl implements CustomerCallService{

	@Autowired
	private CustomerCallRepository callRepo;
	
	@Override
	public Call addCaller(Call call) {
		
		return  callRepo.save(call);
	}

	@Override
	public String Calling(int number) {
		
		return "Wait!";
	}

	@Override
	public Optional<Call> getCaller(int callId) {
		
		return callRepo.findById(callId);
	}

}
