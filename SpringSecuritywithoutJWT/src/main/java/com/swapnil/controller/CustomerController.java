package com.swapnil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.model.Customer;
import com.swapnil.service.CustomerService;

@RestController
public class CustomerController {

	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer cust=customerService.registerCustomer(customer);
		
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("email") String email){
		
		Customer cust=customerService.getCustomerDetailsByEmail(email);
		
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		
		List<Customer> custList=customerService.getAllCustomer();
		
		return new ResponseEntity<List<Customer>>(custList, HttpStatus.OK);
	}
	
	
	
}
