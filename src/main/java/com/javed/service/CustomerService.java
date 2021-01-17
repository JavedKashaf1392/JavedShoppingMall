package com.javed.service;

import java.util.Optional;

import com.javed.entity.Customer;
import com.javed.exception.UserNotFoundException;

public interface CustomerService {
	
	Integer saveCustomer(Customer customer);
	public Optional<Customer> getByCustomerEmail(String email);
	
	//for got password
	public void updateResetPassword(String token,String email) throws UserNotFoundException;
	
	 public void updatePassword(Customer user, String newPassword);
	
	

}
