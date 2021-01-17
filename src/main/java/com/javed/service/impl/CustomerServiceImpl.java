package com.javed.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javed.entity.Customer;
import com.javed.exception.UserNotFoundException;
import com.javed.repo.CustomerRepository;
import com.javed.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService,UserDetailsService{
	@Autowired
	private CustomerRepository repo; //HAS-A
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer saveCustomer(Customer customer) {
		String pwd = customer.getPazzword();
		String encPwd = encoder.encode(pwd);
		customer.setPazzword(encPwd);
		Set<String> role=new HashSet<>() ;
		role.add("CUSTOMER");
		customer.setRoles(role);
		customer = repo.save(customer);
		return customer.getId();
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) 
		
		throws UsernameNotFoundException {
			Optional<Customer> opt = repo.findByEmail(username);
			/*if(!opt.isPresent()|| !opt.get().isActive())*/
			if(!opt.isPresent())
				throw new UsernameNotFoundException("User Not found");
			
			Customer customer = opt.get();
			return new org.springframework.security.core.userdetails.User(
					username, 
					customer.getPazzword(), 
					customer.getRoles()
					.stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toSet())
					);
	}



	@Override
	public Optional<Customer> getByCustomerEmail(String email) {
		return repo.findByEmail(email);
	}
	
	
	
	//email forgot password 
	
		public void updateResetPassword(String token,String email) throws UserNotFoundException {
			 
			
			Customer customer=repo.getByEmail(email);
			if(customer!=null) {
			customer.setResetPasswordToken(token);
				repo.save(customer);
			}else {
				throw new UserNotFoundException("Could not found any user wiht email"+email);
			}
			
		}
		
		
		@Override
		public void updatePassword(Customer user, String newPassword) {
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		        String encodedPassword = passwordEncoder.encode(newPassword);
		        user.setPazzword(encodedPassword);
		        user.setResetPasswordToken(null);
		        repo.save(user);
		}
		
		
		 public Customer getByResetPasswordToken(String token) {
		        return repo.findByResetPasswordToken(token);
		    }



	







	




	
}
