package com.javed.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javed.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByEmail(String  email);
	
//	 @Query("SELECT c FROM Customer c WHERE c.email = ?1")
//	    public Customer findByEmail(String email); 
	     
	    public Customer findByResetPasswordToken(String token);
	    
	    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
	    public Customer getByEmail(String email); 
	
}
