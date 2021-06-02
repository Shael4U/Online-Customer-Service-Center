package com.capgemini.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.entities.Call;
import com.capgemini.service.CustomerCallService;

@RestController
public class CallController {

	@Autowired
	private CustomerCallService callRepo;
	
	@GetMapping("/call/{number}")
	public String Calling(@PathVariable int number)
	{
		return callRepo.Calling(number);
	}
	
	@RequestMapping(value= "/caller/add", method= RequestMethod.POST)
	public Call addCaller(@RequestBody Call newcall)
	{
		return callRepo.addCaller(newcall);
	}
	
	@GetMapping("/callerId/{callId}")
	public Call getCaller(@PathVariable int callId)
	{
		Optional<Call> call =  callRepo.getCaller(callId);
        if(!call.isPresent())
        {
             return new Call();         // returns empty Caller object
        }
        else
            return call.get(); 
	}
	
}
