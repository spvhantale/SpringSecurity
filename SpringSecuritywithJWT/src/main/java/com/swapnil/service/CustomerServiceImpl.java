package com.swapnil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.exception.CustomerException;
import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDao;

	
	

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Optional<Customer> cust=customerDao.findByEmail(customer.getEmail());
		
		if(cust.isPresent()) {
			throw new CustomerException("customer already prensent");
		}
		
		return customerDao.save(customer);
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email) throws CustomerException {
		Optional<Customer> cust=customerDao.findByEmail(email);
		
		if(cust.isPresent()) {

			return cust.get();
		}
		throw new CustomerException("customer not prensent");
		
	}

	@Override
	public List<Customer> getAllCustomer() throws CustomerException {
		List<Customer> customerList=customerDao.findAll();
		if(customerList.isEmpty()) {
			throw new CustomerException("customers are not present");
		}
		return customerList;
	}

}
