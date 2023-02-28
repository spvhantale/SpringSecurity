package com.swapnil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;

@Service
public class CustomerUserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> opt=customerDao.findByEmail(username);
		
		if(opt.isPresent()) {
			Customer customer=opt.get();
			List<GrantedAuthority> authorites=new ArrayList<>();
			
			return new User(customer.getEmail(), customer.getPassword(), authorites);
		}
		
		throw new BadCredentialsException("wrong username");
		
	}

}
