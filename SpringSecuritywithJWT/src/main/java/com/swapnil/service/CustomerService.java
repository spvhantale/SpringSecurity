package com.swapnil.service;

import java.util.List;

import com.swapnil.exception.CustomerException;
import com.swapnil.model.Customer;

public interface CustomerService {

	public Customer registerCustomer(Customer customer)throws CustomerException;
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomer()throws CustomerException;
}
