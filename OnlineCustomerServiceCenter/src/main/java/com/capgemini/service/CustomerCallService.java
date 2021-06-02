package com.capgemini.service;

import java.util.Optional;

import com.capgemini.entities.Call;

public interface CustomerCallService {

	public Call addCaller(Call call);
	public String Calling(int number);
	public Optional<Call> getCaller(int callId);
}
