package com.capgemini.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Issue;
import com.capgemini.service.CustomerDetails;
import com.capgemini.service.CustomerService;
import com.capgemini.entities.Login;


@RestController
public class CustomerController {

	@Autowired
	private CustomerDetails cusService;	
	
	@Autowired
	private CustomerService cusSerImpl;	
	
	
	@RequestMapping(value= "/customer/{id}", method= RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id) 
	{
        Optional<Customer> cus =  cusService.getCustomer(id);
        if(!cus.isPresent())
        {
             return new Customer();         // returns empty Customer object
        }
        else
            return cus.get();              // returns Customer object with data
    }
	
	@RequestMapping(value= "/customer/update/{id}", method= RequestMethod.PUT)
    public Customer updateCustomer(@Valid @RequestBody Customer updcus, @PathVariable int id) {
        Optional<Customer> cus =  cusService.getCustomer(id);
        if (!cus.isPresent()) {
                     System.out.println("Could not find customer with id - " + id);
                     return new Customer();
        }
        else  {
                     updcus.setCustomerId(id);
                     return cusService.updateCustomer(updcus);
        }
	}
	
	@GetMapping("/customer/login/{email}/{password}/{type}")
	public String Login(@Valid @PathVariable String email, @PathVariable String password, @PathVariable String type)
	{
		Login log = new Login();
		log.setUsername(email);
		log.setPassword(password);
		log.setType(type);
		String logRes = cusSerImpl.login(log);
		return logRes;
	}
	
	@RequestMapping(value= "/customer/register", method= RequestMethod.POST)
	public String registerCustomer(@Valid @RequestBody Customer newcus)
	{
		return cusSerImpl.registerCustomer(newcus);
	}
	
	@GetMapping("/customer/changePswd/{email}/{password}")
	public String ChangePassword(@PathVariable String email, @PathVariable String password)
	{
		Login log = new Login();
		log.setUsername(email);
		log.setPassword(password);
		String Chngpswd = cusSerImpl.changePassword(log);
		return Chngpswd;
	}
	
	@GetMapping("/customer/forgotPswd/{email}")
	public String ForgotPassWord(@PathVariable String email)
	{
		String forgotpswd = cusSerImpl.forgotPassword(email);
		return forgotpswd;
	}
	
	@GetMapping("/customer/emailPswd/{id}")
	public Customer EmailPassword(@PathVariable int id)
	{
		Optional<Customer> cus = cusSerImpl.emailPassword(id);
		if(!cus.isPresent())
        {
             return new Customer();        // returns empty Customer object
        }
        else
            return cus.get(); 
	}
	
	@RequestMapping(value= "/customer/issRaise/{email}", method= RequestMethod.POST)
	public Issue raiseIssue(@Valid @RequestBody Issue newiss, @PathVariable String email){       
	        return cusSerImpl.raiseIssue(newiss, email);
	}
	
	@GetMapping("/customer/reopen/{id}")
	public Issue reopenIssue(@PathVariable int id)
	{
		Optional<Issue> iss = cusSerImpl.reopenIssue(id);
		if(!iss.isPresent())
        {
             return new Issue();        // returns empty Issue object
        }
        else
            return iss.get();  
	}
	
	@GetMapping("/customer/viewIssue/{id}")
	public Issue viewIssueById(@PathVariable int id)
	{
		Optional<Issue> iss = cusSerImpl.viewIssueById(id);
		if(!iss.isPresent())
        {
             return new Issue();        // returns empty Issue object
        }
        else
            return iss.get(); 
	}
	
	@GetMapping("/customer/allissue")
	public List<Issue> viewAllIssues()
	{
		return cusSerImpl.viewAllIssues();
	}
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	     
	        ex.getBindingResult().getFieldErrors().forEach(error -> 
	            errors.put(error.getField(), error.getDefaultMessage()));
	         
	        return errors;
	    }

}
