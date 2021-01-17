package com.javed;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javed.entity.Customer;
import com.javed.service.CustomerService;

@SpringBootApplication
public class JavedShoppingMall implements CommandLineRunner{
	
//	@Autowired
//	private CustomerService service;

	public static void main(String[] args) {	
		SpringApplication.run(JavedShoppingMall.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
       
		 /* JDK 9 u can use also Set.of()*/
//			  Set<String>role= new HashSet<>(); 
//				 role.add("CUSTOMER");
//				 role.add("ADMIN");
//				 service.saveCustomer(new Customer(1,"Javed", "khan", "javed@gmail.com","javed","javed", 
//				"Andhra","Vizag","mvp colony sector 11", true,"null",new Date(),new Date(),
//				role.stream().collect(Collectors.toSet())));
						
	}

}
