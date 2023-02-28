package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;

@RestController
public class LoginController {

	
	@Autowired
	private CustomerDAO customerDao;
	
	@GetMapping("/signIn")
	public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		Customer customer=customerDao.findByEmail(auth.getName()).orElseThrow(()->new BadCredentialsException("wrong details"));
		
		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
	}
}
