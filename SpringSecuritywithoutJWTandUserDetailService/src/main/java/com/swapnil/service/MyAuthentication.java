package com.swapnil.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;

@Component
public class MyAuthentication implements AuthenticationProvider{

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private PasswordEncoder pEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		Optional<Customer> opt=customerDao.findByEmail(username);
		
		if(opt.isPresent()) {
			Customer customer=opt.get();
			if(pEncoder.matches(customer.getPassword(), password)) {
				List<GrantedAuthority> authorites=new ArrayList<>();
				
				return new UsernamePasswordAuthenticationToken(username,password , authorites);
			}else {
				throw new BadCredentialsException("wrong password");
			}
		}
		throw new BadCredentialsException("Wrong credetial");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	

}
