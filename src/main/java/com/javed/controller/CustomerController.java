package com.javed.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javed.entity.Customer;
import com.javed.entity.State;
import com.javed.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/customer")
@SessionAttributes("ob")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/signup")
	public String showRegister(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "login";
	}
	
	@PostMapping("/customerSave")
	public String saveUser(@ModelAttribute Customer customer,Model model) {
		Integer id=service.saveCustomer(customer);
		model.addAttribute("message", "your user save with"+id);
		return "login";
		
	}
	
	//----------------------	
		
	@GetMapping("/denied")
	public String showNoAccess() {
		return "DeniedPage";
	}
	
	
	//12.login custom page
	@GetMapping("/login")
	public String showLogin(Model model) {
		//back going to the main page if user enter already
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
	        return "login";
	    }
	    State country=new State();
		model.addAttribute("country", country);
		return "Index";
	}
	
	
	//14.getting data from session 
	//@GetMapping("/setup")
	/*public String doSetup(Principal p,Model model) 
	{
		String email = p.getName();
		System.out.println("--------->"+email);
		Customer customer= service.getByCustomerEmail(email).get();
		model.addAttribute("ob",customer);
		//session.setMaxInactiveInterval(30);
		State country=new State();
		model.addAttribute("country", country);
		return "Index";
	}*/
	
	
	
	
	
	
	
	
}
